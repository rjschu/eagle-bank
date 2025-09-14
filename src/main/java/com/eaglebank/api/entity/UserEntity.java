package com.eaglebank.api.entity;

import com.eaglebank.api.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "users",
    indexes = {
        @Index(name="userEmailIdx", columnList = "email", unique = true)
    })
public class UserEntity implements Patchable<User, UserEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    private String password;

    @CreationTimestamp
    private Instant createdTimestamp;
    @UpdateTimestamp
    private Instant updatedTimestamp;

    public UserEntity(User newUser) {
        this.name = newUser.getName();
        this.email = newUser.getEmail();
        this.phoneNumber = newUser.getPhoneNumber();

        this.address = new AddressEntity(newUser.getAddress());
    }

    public UserEntity(User newUser, String password) {
        this.name = newUser.getName();
        this.email = newUser.getEmail();
        this.phoneNumber = newUser.getPhoneNumber();

        this.address = new AddressEntity(newUser.getAddress());
        this.password = password;
    }

    public UserEntity() {

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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


    public UserEntity patch(User updateFrom) {
        this.name = updateFrom.getName();
        this.email =  updateFrom.getEmail();
        this.phoneNumber = updateFrom.getPhoneNumber();
        this.address = address.patch(updateFrom.getAddress());
        return this;
    }
}
