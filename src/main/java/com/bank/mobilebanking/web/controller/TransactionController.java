package com.bank.mobilebanking.web.controller;

import com.bank.mobilebanking.application.service.TransferService;
import com.bank.mobilebanking.domain.entity.Transaction;
import com.bank.mobilebanking.security.AppUserDetails;
import com.bank.mobilebanking.web.dto.TransferRequest;
import com.bank.mobilebanking.web.dto.TransferResponse;
import com.bank.mobilebanking.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransferService transferService;

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