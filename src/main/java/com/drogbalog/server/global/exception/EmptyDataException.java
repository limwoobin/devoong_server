package com.drogbalog.server.global.exception;

import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import org.springframework.http.HttpStatus;

public class EmptyDataException extends DrogbalogException {
    public EmptyDataException(EmptyDataExceptionType exceptionType) {
        super(HttpStatus.NO_CONTENT , exceptionType.getCode() , exceptionType.getMessage());
    }
}
