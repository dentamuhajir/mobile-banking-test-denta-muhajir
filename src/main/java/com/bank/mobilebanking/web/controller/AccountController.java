package com.bank.mobilebanking.web.controller;

import com.bank.mobilebanking.application.service.AccountService;
import com.bank.mobilebanking.domain.entity.Account;
import com.bank.mobilebanking.security.AppUserDetails;
import com.bank.mobilebanking.web.dto.BalanceResponse;
import com.bank.mobilebanking.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/balance")
    public ApiResponse<BalanceResponse> getBalance(
            @AuthenticationPrincipal AppUserDetails userDetails
    ) {
        Account account = accountService.getBalance(userDetails.getUserId());

        BalanceResponse response = BalanceResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .currency("IDR")
                .build();

        return ApiResponse.success(response);
    }
}