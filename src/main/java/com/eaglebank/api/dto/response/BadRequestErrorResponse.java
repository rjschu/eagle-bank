package com.eaglebank.api.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BadRequestErrorResponse extends ErrorMessage {

    private final List<ErrorDetailsResponse> details = new ArrayList<>();

    public BadRequestErrorResponse(String message) {
        super(message);
    }

    public BadRequestErrorResponse(String message, List<ErrorDetailsResponse> details) {
        super(message);
        this.details.addAll(details);
    }
    
    public List<ErrorDetailsResponse> getDetails() {
        return details;
    }

    public void addDetail(ErrorDetailsResponse detail) {
        details.add(detail);
    }
}
