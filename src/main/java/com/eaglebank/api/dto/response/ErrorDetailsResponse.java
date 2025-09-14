package com.eaglebank.api.dto.response;

public class ErrorDetailsResponse {

    private String field;
    private String message;
    private String type;

    public ErrorDetailsResponse(String field, String message, String type) {
        this.field = field;
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }
}