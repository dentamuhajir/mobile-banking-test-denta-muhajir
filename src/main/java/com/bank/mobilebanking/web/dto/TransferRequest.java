package com.bank.mobilebanking.web.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String destinationAccountNumber;
    private BigDecimal amount;
    private String description;
}