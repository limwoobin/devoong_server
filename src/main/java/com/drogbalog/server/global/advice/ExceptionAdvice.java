package com.drogbalog.server.global.advice;

import com.drogbalog.server.global.exception.DrogbalogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DrogbalogException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<DrogbalogException> handleException(Exception e) {
        DrogbalogException exception = new DrogbalogException();
        exception.setMessage(e.getMessage());
        exception.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exception , HttpStatus.BAD_REQUEST);
    }
}
