package com.lynhatkhanh.educationweb.educationweb.controller;

import com.lynhatkhanh.educationweb.educationweb.dto.request.AuthenticationRequest;
import com.lynhatkhanh.educationweb.educationweb.service.IAuthenticationService;
import com.lynhatkhanh.educationweb.educationweb.utils.MessageUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {

    IAuthenticationService authenticationService;

    @GetMapping
    public String showMyLoginPageWithMessage() {
        System.out.println("Login page!");
        return "/login/login";
    }

}
