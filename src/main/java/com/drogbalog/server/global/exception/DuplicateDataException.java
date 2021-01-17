package com.drogbalog.server.global.exception;


import org.springframework.http.HttpStatus;

public class DuplicateDataException extends DrogbalogException {

    public DuplicateDataException(HttpStatus status) {
        super(status);
    }

    public DuplicateDataException(String message) {
        super(HttpStatus.CONFLICT , message);
    }

    public DuplicateDataException(int code , String message) {
        super(HttpStatus.CONFLICT , code , message);
    }
}
