package com.bank.mobilebanking.infrastructure.persistence;

import com.bank.mobilebanking.domain.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LedgerRepository extends JpaRepository<LedgerEntry, UUID> {
}