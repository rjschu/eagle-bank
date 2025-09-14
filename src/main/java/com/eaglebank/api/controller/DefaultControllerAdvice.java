package com.eaglebank.api.controller;

import com.eaglebank.api.dto.response.BadRequestErrorResponse;
import com.eaglebank.api.dto.response.ErrorDetailsResponse;
import com.eaglebank.api.dto.response.ErrorMessage;
import com.eaglebank.api.exception.UserNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BadRequestErrorResponse handleInvalidArgumentException(MethodArgumentNotValidException ex) {
        BadRequestErrorResponse errorResponse = new BadRequestErrorResponse("Invalid request body");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String defaultMessage = error.getDefaultMessage();
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String type = Objects.requireNonNull(ex.getFieldType(field)).getSimpleName();
            errorResponse.addDetail(new ErrorDetailsResponse(field, defaultMessage, type));
        });
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleException(Exception ex) {
        return new ErrorMessage(ex.getMessage());
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public ErrorMessage handleSignatureException(SignatureException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}
