package com.drogbalog.server.global.exception;

import com.drogbalog.server.global.exception.messages.ExceptionType;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DrogbalogException {

    public UserNotFoundException(HttpStatus status) {
        super(status);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(ExceptionType exceptionType) {
        super(HttpStatus.NOT_FOUND , exceptionType.getMessage());
    }
}
