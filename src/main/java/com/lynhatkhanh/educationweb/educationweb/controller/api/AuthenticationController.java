package com.lynhatkhanh.educationweb.educationweb.controller.api;

import com.lynhatkhanh.educationweb.educationweb.dto.request.AuthenticationRequest;
import com.lynhatkhanh.educationweb.educationweb.dto.response.ApiResponse;
import com.lynhatkhanh.educationweb.educationweb.dto.response.AuthenticationResponse;
import com.lynhatkhanh.educationweb.educationweb.service.IAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    IAuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

}
