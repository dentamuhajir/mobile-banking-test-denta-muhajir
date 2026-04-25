package com.bank.mobilebanking.infrastructure.persistence;

import com.bank.mobilebanking.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}