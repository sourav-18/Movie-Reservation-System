package com.ms.backend.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUploadException extends RuntimeException {

    private final HttpStatus status;

    public InvalidUploadException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
