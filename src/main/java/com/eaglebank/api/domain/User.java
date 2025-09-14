package com.eaglebank.api.domain;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Address address;
    private final Instant createdTimestamp;
    private final Instant updatedTimestamp;
    private final String password;

    public User(String name, String email, String phoneNumber, Address address, String password) {
        this(null, name, email, phoneNumber, address, null, null, password);
    }

    public User(Long id, String name, String email, String phoneNumber, Address address,  Instant createdTimestamp, Instant updatedTimestamp, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = String.valueOf(id);
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }
}
