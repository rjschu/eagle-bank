package com.eaglebank.api.domain;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public class User {

    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final Address address;
    private final Instant createdTimestamp;
    private final Instant updatedTimestamp;
    private final String password;

    private User(Long id, String name, String email, String phoneNumber, Address address,  Instant createdTimestamp, Instant updatedTimestamp, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
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

    public Long getId() {
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


    public static final class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private String phoneNumber;
        private Address address;
        private Instant createdTimestamp;
        private Instant updatedTimestamp;
        private String password;

        public UserBuilder() {
        }

        public UserBuilder(User other) {
            this.id = other.id;
            this.name = other.name;
            this.email = other.email;
            this.phoneNumber = other.phoneNumber;
            this.address = other.address;
            this.createdTimestamp = other.createdTimestamp;
            this.updatedTimestamp = other.updatedTimestamp;
            this.password = other.password;
        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder withCreatedTimestamp(Instant createdTimestamp) {
            this.createdTimestamp = createdTimestamp;
            return this;
        }

        public UserBuilder withUpdatedTimestamp(Instant updatedTimestamp) {
            this.updatedTimestamp = updatedTimestamp;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(id, name, email, phoneNumber, address, createdTimestamp, updatedTimestamp, password);
        }
    }
}
