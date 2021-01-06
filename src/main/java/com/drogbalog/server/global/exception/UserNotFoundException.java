package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DrogbalogException {

    public UserNotFoundException(HttpStatus status) {
        super(status);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(HttpStatus status , String message) {
        super(status , message);
    }
}
