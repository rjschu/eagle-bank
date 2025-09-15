package com.eaglebank.api.repository;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByUserId(Long userId);

    AccountEntity findByAccountNumber(String accountNumber);

    @Modifying
    @Query(value = "update AccountEntity a set a.balance = ?2 where a.id = ?1")
    void updateBalance(Long id, Double amount);
}
