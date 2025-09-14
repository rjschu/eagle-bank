package com.eaglebank.api.dto.response;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.enums.AccountType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public record AccountResponse(Long id, String name, AccountType accountType, String sortCode, String accountNumber,
                              Double balance, String currency,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant createdTimestamp,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant updatedTimestamp) {
    public AccountResponse(Account account) {
        this(account.getId(), account.getName(), account.getAccountType(), account.getSortCode(),
                account.getAccountNumber(), account.getBalance(), account.getCurrency(), account.getCreatedTimestamp(),
                account.getUpdatedTimestamp());
    }
}
