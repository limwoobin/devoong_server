package com.drogbalog.server.global.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends DrogbalogException {
    public static final BadRequestException INVALID_EMAIL = new BadRequestException(InvalidDataType.EMAIL);
    public static final BadRequestException INVALID_PASSWORD = new BadRequestException(InvalidDataType.EMAIL);


    public BadRequestException(HttpStatus status) {
        super(status);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(InvalidDataType dataType) {
        super(dataType.getCode() , dataType.getMessage());
    }

    public BadRequestException(HttpStatus status , String message) {
        super(status , message);
    }
}
