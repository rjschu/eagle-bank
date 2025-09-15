package com.eaglebank.api.dto.response;

import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.enums.TransactionType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public record TransactionResponse(String id, Double amount, String currency, TransactionType transactionType, String reference,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant createdTimestamp) {
    public TransactionResponse(Transaction transaction) {
        this(String.format("tan-%d",transaction.getId()), transaction.getAmount(), transaction.getCurrency(),
                transaction.getType(), transaction.getReference(), transaction.getCreatedTimestamp());
    }
}
