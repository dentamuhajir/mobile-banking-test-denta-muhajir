package com.bank.mobilebanking.web.controller;

import com.bank.mobilebanking.application.service.AccountService;
import com.bank.mobilebanking.domain.entity.Account;
import com.bank.mobilebanking.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public Map<String, Object> getBalance(
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        Account account = accountService.getBalance(userDetails.getUserId());

        return Map.of(
                "accountNumber", account.getAccountNumber(),
                "balance", account.getBalance(),
                "currency", "IDR"
        );
    }
}