package com.drogbalog.server.global.exception;


import com.drogbalog.server.global.exception.messages.DuplicateExceptionType;
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

    public DuplicateDataException(DuplicateExceptionType duplicateExceptionType) {
        super(HttpStatus.CONFLICT , duplicateExceptionType.getCode() , duplicateExceptionType.getMessage());
    }
}
