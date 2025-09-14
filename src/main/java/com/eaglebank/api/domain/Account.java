package com.eaglebank.api.domain;

import com.eaglebank.api.enums.AccountType;

import java.time.Instant;

public class Account {

    private final Long id;
    private final String name;
    private final AccountType accountType;
    private final String sortCode = "10-10-10";
    private final String accountNumber;
    private final Double balance;
    private final String currency = "GBP";
    private final Instant createdTimestamp;
    private final Instant updatedTimestamp;
    private final User accountOwner;

    private Account(Long id, Double balance, String name, AccountType accountType,
                    Instant createdTimestamp, Instant updatedTimestamp, String accountNumber, User accountOwner) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.accountType = accountType;
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
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

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public User getAccountOwner() {
        return accountOwner;
    }

    public static final class AccountBuilder {
        private Long id;
        private String name;
        private AccountType accountType;
        private String accountNumber;
        private Double balance;
        private Instant createdTimestamp;
        private Instant updatedTimestamp;
        private User accountOwner;

        public AccountBuilder() {
        }

        public AccountBuilder(Account other) {
            this.id = other.id;
            this.name = other.name;
            this.accountType = other.accountType;
            this.accountNumber = other.accountNumber;
            this.balance = other.balance;
            this.createdTimestamp = other.createdTimestamp;
            this.updatedTimestamp = other.updatedTimestamp;
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public AccountBuilder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder withBalance(Double balance) {
            this.balance = balance;
            return this;
        }


        public AccountBuilder withCreatedTimestamp(Instant createdTimestamp) {
            this.createdTimestamp = createdTimestamp;
            return this;
        }

        public AccountBuilder withUpdatedTimestamp(Instant updatedTimestamp) {
            this.updatedTimestamp = updatedTimestamp;
            return this;
        }

        public AccountBuilder withAccountOwner(User accountOwner) {
            this.accountOwner = accountOwner;
            return this;
        }

        public Account build() {
            return new Account(id, balance, name, accountType, createdTimestamp, updatedTimestamp, accountNumber, accountOwner);
        }
    }
}
