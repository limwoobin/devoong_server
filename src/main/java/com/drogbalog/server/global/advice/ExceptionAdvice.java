package com.drogbalog.server.global.advice;

import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DrogbalogException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> badRequestException(Exception e) {
        ErrorResponse errorResponse = makeExceptionObject(HttpStatus.BAD_REQUEST , e);
        return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeExceptionObject(HttpStatus status , Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status);
        errorResponse.setCode(status.value());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
