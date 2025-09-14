package com.eaglebank.api.repository;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByUserId(Long userId);
}
