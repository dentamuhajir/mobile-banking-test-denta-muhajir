package com.bank.mobilebanking.application.service;

import com.bank.mobilebanking.domain.entity.*;
import com.bank.mobilebanking.domain.exception.BusinessException;
import com.bank.mobilebanking.infrastructure.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final LedgerRepository ledgerRepository;

    @Transactional
    public Transaction transfer(UUID userId,
                                String destinationAccountNumber,
                                BigDecimal amount,
                                String description) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(
                    "INVALID_AMOUNT",
                    "Amount must be greater than zero",
                    HttpStatus.BAD_REQUEST
            );
        }

        Account sender = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(
                        "SENDER_NOT_FOUND",
                        "Sender account not found",
                        HttpStatus.NOT_FOUND
                ));

        Account receiver = accountRepository.findByAccountNumber(destinationAccountNumber)
                .orElseThrow(() -> new BusinessException(
                        "RECEIVER_NOT_FOUND",
                        "Destination account not found",
                        HttpStatus.NOT_FOUND
                ));

        if (sender.getId().equals(receiver.getId())) {
            throw new BusinessException(
                    "CANNOT_TRANSFER_TO_SELF",
                    "Cannot transfer to the same account",
                    HttpStatus.BAD_REQUEST
            );
        }

        Account senderLocked = accountRepository.findByIdForUpdate(sender.getId())
                .orElseThrow(() -> new BusinessException(
                        "SENDER_NOT_FOUND",
                        "Sender account not found",
                        HttpStatus.NOT_FOUND
                ));

        Account receiverLocked = accountRepository.findByIdForUpdate(receiver.getId())
                .orElseThrow(() -> new BusinessException(
                        "RECEIVER_NOT_FOUND",
                        "Destination account not found",
                        HttpStatus.NOT_FOUND
                ));

        if (senderLocked.getBalance().compareTo(amount) < 0) {
            throw new BusinessException(
                    "INSUFFICIENT_BALANCE",
                    "Insufficient balance",
                    HttpStatus.BAD_REQUEST
            );
        }

        // UPDATE BALANCE
        senderLocked.setBalance(senderLocked.getBalance().subtract(amount));
        receiverLocked.setBalance(receiverLocked.getBalance().add(amount));

        // TRANSACTION
        Transaction tx = Transaction.builder()
                .id(UUID.randomUUID())
                .referenceNo(generateRef())
                .type("TRANSFER")
                .status("SUCCESS")
                .sourceAccountId(senderLocked.getId())
                .destinationAccountId(receiverLocked.getId())
                .amount(amount)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(tx);

        // LEDGER
        ledgerRepository.save(LedgerEntry.builder()
                .id(UUID.randomUUID())
                .transactionId(tx.getId())
                .accountId(senderLocked.getId())
                .entryType("DEBIT")
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .build());

        ledgerRepository.save(LedgerEntry.builder()
                .id(UUID.randomUUID())
                .transactionId(tx.getId())
                .accountId(receiverLocked.getId())
                .entryType("CREDIT")
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .build());

        return tx;
    }

    private String generateRef() {
        return "TX-" + System.currentTimeMillis();
    }
}