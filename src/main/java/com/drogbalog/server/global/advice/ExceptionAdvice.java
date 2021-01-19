package com.drogbalog.server.global.advice;

import com.drogbalog.server.global.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DrogbalogException.class)
    public ResponseEntity<ErrorResponse> handleException(DrogbalogException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(e.getCode());
        errorResponse.setStatus(e.getStatus());
        return new ResponseEntity<>(errorResponse , e.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> badRequestException(Exception e) {
        ErrorResponse errorResponse = makeExceptionObject(HttpStatus.BAD_REQUEST , e);
        return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> notFoundException(Exception e) {
        ErrorResponse errorResponse = makeExceptionObject(HttpStatus.NOT_FOUND , e);
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnAuthorizedException.class , AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> unAuthorizedException(Exception e) {
        ErrorResponse errorResponse = makeExceptionObject(HttpStatus.UNAUTHORIZED , e);
        return new ResponseEntity<>(errorResponse , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> duplicateKeyException(Exception e) {
        ErrorResponse errorResponse = makeExceptionObject(HttpStatus.CONFLICT , e);
        return new ResponseEntity<>(errorResponse , HttpStatus.CONFLICT);
    }


    private ErrorResponse makeExceptionObject(HttpStatus status , Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status);
        errorResponse.setCode(status.value());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
