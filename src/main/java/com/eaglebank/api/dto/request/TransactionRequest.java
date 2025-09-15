package com.eaglebank.api.dto.request;

import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest(@NotNull Double amount, String currency, TransactionType type, String reference) {
    public Transaction toDomain() {
        return Transaction.TransactionBuilder.aTransaction()
                .withAmount(amount)
                .withCurrency(currency)
                .withType(type)
                .withReference(reference)
                .build();

    }
}
