package com.bank.mobilebanking.web.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceResponse {
    private String accountNumber;
    private String username;
    private BigDecimal balance;
    private String currency;
}