package com.eaglebank.api.domain;

import com.eaglebank.api.enums.TransactionType;

import java.time.Instant;

public class Transaction {

    private Long id;
    private Double amount;
    private String currency;
    private TransactionType type;
    private String reference;
    private String userId;
    private Instant createdTimestamp;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public TransactionType getType() {
        return type;
    }

    public String getReference() {
        return reference;
    }

    public String getUserId() {
        return userId;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }


    public static final class TransactionBuilder {
        private Long id;
        private Double amount;
        private String currency;
        private TransactionType type;
        private String reference;
        private String userId;
        private Instant createdTimestamp;

        public TransactionBuilder() {
        }

        public TransactionBuilder(Transaction other) {
            this.amount = other.amount;
            this.currency = other.currency;
            this.type = other.type;
            this.reference = other.reference;
            this.userId = other.userId;
            this.createdTimestamp = other.createdTimestamp;
        }

        public static TransactionBuilder aTransaction() {
            return new TransactionBuilder();
        }


        public TransactionBuilder witId(Long id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public TransactionBuilder withType(TransactionType type) {
            this.type = type;
            return this;
        }

        public TransactionBuilder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        public TransactionBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public TransactionBuilder withCreatedTimestamp(Instant createdTimestamp) {
            this.createdTimestamp = createdTimestamp;
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.id = this.id;
            transaction.amount = this.amount;
            transaction.userId = this.userId;
            transaction.type = this.type;
            transaction.currency = this.currency;
            transaction.createdTimestamp = this.createdTimestamp;
            transaction.reference = this.reference;
            return transaction;
        }
    }
}
