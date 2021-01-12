package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends DrogbalogException {
    public UnAuthorizedException(HttpStatus status) {
        super(status);
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(HttpStatus status , String message) {
        super(status , message);
    }
}