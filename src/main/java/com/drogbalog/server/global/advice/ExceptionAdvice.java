package com.drogbalog.server.global.advice;

import com.drogbalog.server.global.exception.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DevoongException.class)
    public ResponseEntity<ErrorResponse> handleException(DevoongException e) {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        BindingResult bindingResult = e.getBindingResult();

        for (FieldError error : bindingResult.getFieldErrors()) {
            errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(error.getDefaultMessage())
                    .build();

            return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        BindingResult bindingResult = e.getBindingResult();

        for (FieldError error : bindingResult.getFieldErrors()) {
            errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(error.getDefaultMessage())
                    .build();

            return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(errorResponse , HttpStatus.INTERNAL_SERVER_ERROR);
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
