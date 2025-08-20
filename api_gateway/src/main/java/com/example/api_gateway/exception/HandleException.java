package com.example.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleException(ValidationException ex) {
        return new ResponseEntity<>(Map.of(
                "status", HttpStatus.UNAUTHORIZED.value(),
                "message", ex.getMessage()
        ), HttpStatus.UNAUTHORIZED);
    }

}