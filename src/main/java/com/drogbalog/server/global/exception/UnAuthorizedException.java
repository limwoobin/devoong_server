package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends DrogbalogException {
    public UnAuthorizedException(HttpStatus status) {
        super(status);
    }

    public UnAuthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED , message);
    }

    public UnAuthorizedException(int code , String message) {
        super(HttpStatus.UNAUTHORIZED , code , message);
    }
}
