package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidJwtTokenException extends DrogbalogException {
    public InvalidJwtTokenException(HttpStatus status) {
        super(status);
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }

    public InvalidJwtTokenException(InvalidDataType dataType) {
        super(dataType.getCode() , dataType.getMessage());
    }

    public InvalidJwtTokenException(HttpStatus status , String message) {
        super(status , message);
    }
}
