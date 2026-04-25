package com.bank.mobilebanking.web.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDetailResponse {
    private String transactionId;
    private String referenceNo;
    private String type;
    private String status;
    private BigDecimal amount;
    private String sourceAccount;
    private String destinationAccount;
    private LocalDateTime createdAt;
}