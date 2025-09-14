package com.eaglebank.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {

    PERSONAL("personal");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    @JsonCreator
    public static AccountType forValue(String value) {
        return AccountType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.accountType;
    }
}
