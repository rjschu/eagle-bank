package com.eaglebank.api.domain;

public class Address {

    private final String line1;
    private final String line2;
    private final String line3;
    private final String town;
    private final String county;
    private final String postcode;

    public Address(String line1, String line2, String line3,
                   String town, String county, String postcode) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
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
