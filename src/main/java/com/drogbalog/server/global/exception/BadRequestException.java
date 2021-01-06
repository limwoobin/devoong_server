package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends DrogbalogException {

    public BadRequestException(HttpStatus status) {
        super(status);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(HttpStatus status , String message) {
        super(status , message);
    }
}
