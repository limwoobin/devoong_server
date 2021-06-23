package com.drogbalog.server.global.exception;

import com.drogbalog.server.global.exception.auth.AuthExceptionCode;
import com.drogbalog.server.global.exception.messages.ExceptionType;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends DrogbalogException {
    public UnAuthorizedException(ExceptionType exceptionType) {
        super(HttpStatus.UNAUTHORIZED , exceptionType.getMessage());
    }

    public UnAuthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED , message);
    }

    public UnAuthorizedException(int code , String message) {
        super(HttpStatus.UNAUTHORIZED , code , message);
    }

    public UnAuthorizedException(ExceptionType exceptionType , AuthExceptionCode authExceptionCode) {
        super(HttpStatus.UNAUTHORIZED , authExceptionCode.getCode() , exceptionType.getMessage());
    }


}
