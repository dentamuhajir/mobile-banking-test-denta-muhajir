package com.bank.mobilebanking.application.service;

import com.bank.mobilebanking.domain.entity.Account;
import com.bank.mobilebanking.domain.exception.BusinessException;
import com.bank.mobilebanking.infrastructure.persistence.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getBalance(UUID userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(
                        "ACCOUNT_NOT_FOUND",
                        "Account not found for this user",
                        HttpStatus.NOT_FOUND
                ));
    }
}