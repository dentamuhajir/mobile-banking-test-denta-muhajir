package com.bank.mobilebanking.web.controller;


import com.bank.mobilebanking.application.service.AuthService;
import com.bank.mobilebanking.web.dto.LoginResponse;
import com.bank.mobilebanking.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody Map<String, String> req) {

        String token = authService.login(
                req.get("username"),
                req.get("password")
        );

        LoginResponse response = LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();

        return ApiResponse.success(response);
    }
}