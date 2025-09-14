package com.eaglebank.api.dto.request;

import com.eaglebank.api.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserRequest(@NotBlank String name,
                               @NotBlank @Email String email,
                               @NotBlank
                               @Pattern(regexp = "^\\+[1-9]\\d{1,14}$") String phoneNumber,
                               @Valid AddressRequest address) {

    public User toDomain() {
        return User.UserBuilder.builder()
                .withName(name)
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withAddress(address.toDomain())
                .build();
    }

}
