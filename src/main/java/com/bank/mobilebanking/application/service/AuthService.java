package com.bank.mobilebanking.application.service;

import com.bank.mobilebanking.security.AppUserDetails;
import com.bank.mobilebanking.security.AppUserDetailsService;
import com.bank.mobilebanking.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public String login(String username, String password) {

        var userDetails = (AppUserDetails)
                userDetailsService.loadUserByUsername(username);

        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("INVALID_CREDENTIALS");
        }

        return jwtService.generateToken(userDetails);
    }
}