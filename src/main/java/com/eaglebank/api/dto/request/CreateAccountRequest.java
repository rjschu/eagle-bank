package com.eaglebank.api.dto.request;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(@NotBlank String name,
                                   @NotNull AccountType accountType) {

    public Account toDomain() {

        return Account.AccountBuilder.anAccount()
                .withName(name).withAccountType(accountType).build();

    }

}
