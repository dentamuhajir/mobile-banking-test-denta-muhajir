package com.bank.mobilebanking.web.controller;

import com.bank.mobilebanking.application.service.TransactionHistoryService;
import com.bank.mobilebanking.application.service.TransferService;
import com.bank.mobilebanking.domain.entity.Transaction;
import com.bank.mobilebanking.security.AppUserDetails;
import com.bank.mobilebanking.web.dto.TransactionDetailResponse;
import com.bank.mobilebanking.web.dto.TransactionListResponse;
import com.bank.mobilebanking.web.dto.TransferRequest;
import com.bank.mobilebanking.web.dto.TransferResponse;
import com.bank.mobilebanking.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransferService transferService;
    private final TransactionHistoryService transactionHistoryService;

    @GetMapping
    public ApiResponse<List<TransactionListResponse>> getTransactions(
            @AuthenticationPrincipal AppUserDetails user
    ) {
        var result = transactionHistoryService.getUserTransactions(user.getUserId());
        return ApiResponse.success(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<TransactionDetailResponse> getDetail(
            @PathVariable UUID id,
            @AuthenticationPrincipal AppUserDetails user
    ) {
        var result = transactionHistoryService.getDetail(user.getUserId(), id);
        return ApiResponse.success(result);
    }

    @PostMapping("/transfer")
    public ApiResponse<TransferResponse> transfer(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody TransferRequest request
    ) {
        Transaction tx = transferService.transfer(
                user.getUserId(),
                request.getDestinationAccountNumber(),
                request.getAmount(),
                request.getDescription()
        );

        TransferResponse response = TransferResponse.builder()
                .referenceNo(tx.getReferenceNo())
                .amount(tx.getAmount())
                .status(tx.getStatus())
                .build();

        return ApiResponse.success(response);
    }
}