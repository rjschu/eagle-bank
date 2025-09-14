package com.eaglebank.api.entity;

import com.eaglebank.api.domain.Address;
import com.eaglebank.api.dto.AddressRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line1;
    private String line2;
    private String line3;
    private String town;
    private String county;
    private String postcode;

    @OneToMany(mappedBy = "address")
    private List<UserEntity> users;


    public AddressEntity(Address address) {
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
        this.line3 = address.getLine3();
        this.town = address.getTown();
        this.county = address.getCounty();
        this.postcode = address.getPostcode();
    }

    public AddressEntity() {

    }


    public Long getId() {
        return id;
    }


    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }
}
