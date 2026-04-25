package com.bank.mobilebanking.application.service;

import com.bank.mobilebanking.domain.entity.Account;
import com.bank.mobilebanking.domain.entity.Transaction;
import com.bank.mobilebanking.domain.exception.BusinessException;
import com.bank.mobilebanking.infrastructure.persistence.AccountRepository;
import com.bank.mobilebanking.infrastructure.persistence.TransactionRepository;
import com.bank.mobilebanking.web.dto.TransactionDetailResponse;
import com.bank.mobilebanking.web.dto.TransactionListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public List<TransactionListResponse> getUserTransactions(UUID userId) {

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(
                        "ACCOUNT_NOT_FOUND",
                        "Account not found",
                        HttpStatus.NOT_FOUND
                ));

        List<Transaction> transactions =
                transactionRepository.findBySourceAccountIdOrDestinationAccountId(
                        account.getId(),
                        account.getId()
                );

        return transactions.stream()
                .map(tx -> {
                    String direction = tx.getSourceAccountId().equals(account.getId())
                            ? "OUT"
                            : "IN";

                    return TransactionListResponse.builder()
                            .transactionId(tx.getId().toString())
                            .type(tx.getType())
                            .amount(tx.getAmount())
                            .status(tx.getStatus())
                            .direction(direction)
                            .createdAt(tx.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public TransactionDetailResponse getDetail(UUID userId, UUID transactionId) {

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(
                        "ACCOUNT_NOT_FOUND",
                        "Account not found",
                        HttpStatus.NOT_FOUND
                ));

        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new BusinessException(
                        "TRANSACTION_NOT_FOUND",
                        "Transaction not found",
                        HttpStatus.NOT_FOUND
                ));

        if (!tx.getSourceAccountId().equals(account.getId()) &&
                !tx.getDestinationAccountId().equals(account.getId())) {

            throw new BusinessException(
                    "FORBIDDEN",
                    "You are not allowed to access this transaction",
                    HttpStatus.FORBIDDEN
            );
        }

        Account source = accountRepository.findById(tx.getSourceAccountId()).orElse(null);
        Account dest = accountRepository.findById(tx.getDestinationAccountId()).orElse(null);

        return TransactionDetailResponse.builder()
                .transactionId(tx.getId().toString())
                .referenceNo(tx.getReferenceNo())
                .type(tx.getType())
                .status(tx.getStatus())
                .amount(tx.getAmount())
                .sourceAccount(source != null ? source.getAccountNumber() : null)
                .destinationAccount(dest != null ? dest.getAccountNumber() : null)
                .createdAt(tx.getCreatedAt())
                .build();
    }
}