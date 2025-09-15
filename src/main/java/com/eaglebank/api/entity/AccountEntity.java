package com.eaglebank.api.entity;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.enums.AccountType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;

@Entity
@Table(name = "account", indexes = @Index(name = "userIdIdx", columnList = "user_id"))

public class AccountEntity implements Patchable<Account, AccountEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private AccountType accountType;
    private String sortCode;

    @Column(nullable = false, length = 8, unique = true)
    private String accountNumber;
    @Column(nullable = false, length = 7)
    private Double balance;
    private String currency;

    @CreationTimestamp
    private Instant createdTimestamp;
    @UpdateTimestamp
    private Instant updatedTimestamp;

    @ManyToOne
    @JoinColumn(name = "userId", unique = false)
    private UserEntity user;

    public AccountEntity() {
    }

    public AccountEntity(Account account, UserEntity user) {
        this.name = account.getName();
        this.accountType = account.getAccountType();
        this.sortCode = account.getSortCode();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.currency = account.getCurrency();
        this.createdTimestamp = account.getCreatedTimestamp();
        this.updatedTimestamp = account.getUpdatedTimestamp();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getSortCode() {
        return sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public UserEntity getUser() {
        return user;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    @Override
    public AccountEntity patch(Account patched) {
        return null;
    }
}
