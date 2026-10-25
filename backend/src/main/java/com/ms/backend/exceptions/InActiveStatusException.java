package com.ms.backend.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public class InActiveStatusException extends RuntimeException {
    private final HttpStatus status;
    public InActiveStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
