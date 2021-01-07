package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class EmptyDataException extends DrogbalogException {
    public EmptyDataException(HttpStatus status) {
        super(status);
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(HttpStatus status , String message) {
        super(status , message);
    }
}
