package com.eaglebank.api.entity;

import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;

@Entity
@Table(name="transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.ORDINAL)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private String reference;

    @ManyToOne
    @JoinColumn(name="accountId")
    private AccountEntity accountId;

    @CreationTimestamp
    private Instant createdTimestamp;

    public TransactionEntity(Transaction transaction, AccountEntity accountEntity) {
        this.amount = transaction.getAmount();
        this.currency = transaction.getCurrency();
        this.transactionType = transaction.getType();
        this.reference = transaction.getReference();
        this.accountId = accountEntity;
        this.createdTimestamp = transaction.getCreatedTimestamp();
    }

    public TransactionEntity() {

    }


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
        return transactionType;
    }

    public String getReference() {
        return reference;
    }

    public AccountEntity getAccount() {
        return accountId;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }
}
