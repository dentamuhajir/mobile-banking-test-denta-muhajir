package com.bank.mobilebanking.web.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionListResponse {
    private String transactionId;
    private String type;
    private BigDecimal amount;
    private String status;
    private String direction; // IN / OUT
    private LocalDateTime createdAt;
}