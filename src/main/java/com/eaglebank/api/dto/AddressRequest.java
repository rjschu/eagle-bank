package com.eaglebank.api.dto;

import com.eaglebank.api.domain.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressRequest(@NotBlank String line1, String line2, String line3, @NotBlank String town,
                             @NotBlank String county, @NotBlank String postcode) {

    public Address toDomain() {
        return new Address(line1, line2, line3, town, county, postcode);
    }
}
