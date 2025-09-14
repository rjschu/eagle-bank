package com.eaglebank.api.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public record UserResponse(String id,
                           String name,
                           String email,
                           String phoneNumber,
                           AddressResponse address,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant createdTimestamp,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant updatedTimestamp) {

    @Override
    public String id() {
        return String.format("usr-%s",id);
    }
}
