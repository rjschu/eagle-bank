package com.eaglebank.api.enums;

import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.BiFunction;

public enum TransactionType {

    DEPOSIT("deposit"),
    WITHDRAW("withdraw");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TransactionType forValue(String value) {
        return TransactionType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }

}
