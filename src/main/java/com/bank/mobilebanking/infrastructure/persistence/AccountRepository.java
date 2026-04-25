package com.bank.mobilebanking.infrastructure.persistence;

import com.bank.mobilebanking.domain.entity.Account;
import org.springframework.data.jpa.repository.*;
import jakarta.persistence.LockModeType;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUserId(UUID userId);

    Optional<Account> findByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdForUpdate(UUID id);
}