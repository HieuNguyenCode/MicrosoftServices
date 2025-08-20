package com.example.api_gateway.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ValidationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ValidationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
