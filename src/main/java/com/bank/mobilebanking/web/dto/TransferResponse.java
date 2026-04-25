package com.bank.mobilebanking.web.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransferResponse {
    private String referenceNo;
    private BigDecimal amount;
    private String status;
}