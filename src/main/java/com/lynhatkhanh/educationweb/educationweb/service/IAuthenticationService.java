package com.lynhatkhanh.educationweb.educationweb.service;

import com.lynhatkhanh.educationweb.educationweb.dto.request.AuthenticationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.IntrospectRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.LogoutRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.request.RefreshRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.AuthenticationResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.IntrospectResponse;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface IAuthenticationService {

    String generateToken(User user);

    AuthenticationResponse authenticated(AuthenticationRequest request);

    SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException;

    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    String buildScope(User user);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

}
