package com.bank.mobilebanking.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {

    @Id
    private UUID id;

    @Column(name = "reference_no")
    private String referenceNo;

    private String type;
    private String status;

    @Column(name = "source_account_id")
    private UUID sourceAccountId;

    @Column(name = "destination_account_id")
    private UUID destinationAccountId;

    private BigDecimal amount;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}