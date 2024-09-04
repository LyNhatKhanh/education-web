package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.InvalidedTokenRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.UserRepository;
import com.lynhatkhanh.educationweb.educationweb.dto.request.AuthenticationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.IntrospectRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.LogoutRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.RefreshRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.AuthenticationResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.IntrospectResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.InvalidedToken;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import com.lynhatkhanh.educationweb.educationweb.exception.AppException;
import com.lynhatkhanh.educationweb.educationweb.exception.ErrorCode;
import com.lynhatkhanh.educationweb.educationweb.service.IAuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService {

    UserRepository userRepository;
    InvalidedTokenRepository invalidedTokenRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    private long VALID_TIME;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    private long REFRESHABLE_TIME;


    @Override
    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .issuer("education-web")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_TIME, ChronoUnit.SECONDS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.info("Cannot generate token!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResponse authenticated(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isAuthenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .isAuthenticated(true)
                .token(token)
                .build();
    }

    @Override
    public SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        boolean isVerified = signedJWT.verify(jwsVerifier);

        Date expiryTime = isRefresh
                ? new Date(signedJWT
                    .getJWTClaimsSet()
                    .getIssueTime()
                    .toInstant()
                    .plus(REFRESHABLE_TIME, ChronoUnit.SECONDS)
                    .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!(isVerified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        Boolean isValid = true;

        try {
            verifyToken(request.getToken(), false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .isValid(isValid)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        String token = request.getToken();
        SignedJWT signedJWT = verifyToken(token, true);
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidedToken invalidedToken = InvalidedToken.builder()
                .id(jwtId)
                .expiryTime(expiryTime)
                .build();
        invalidedTokenRepository.save(invalidedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    @Override
    public String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            SignedJWT signedJWT = verifyToken(request.getToken(), true);
            String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            InvalidedToken invalidedToken = InvalidedToken.builder()
                    .id(jwtId)
                    .expiryTime(expiryTime)
                    .build();

            invalidedTokenRepository.save(invalidedToken);
        } catch (AppException ex) {
            log.info("Token has already expired!");
        }
    }
}
