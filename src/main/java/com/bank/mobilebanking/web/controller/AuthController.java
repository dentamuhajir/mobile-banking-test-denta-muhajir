package com.bank.mobilebanking.web.controller;


import com.bank.mobilebanking.application.service.AuthService;
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
    public Map<String, Object> login(@RequestBody Map<String, String> req) {

        String token = authService.login(
                req.get("username"),
                req.get("password")
        );

        return Map.of(
                "accessToken", token,
                "tokenType", "Bearer"
        );
    }
}