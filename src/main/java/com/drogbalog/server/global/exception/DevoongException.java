package com.drogbalog.server.global.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DevoongException extends RuntimeException {
    private HttpStatus status;
    private int code;
    private String message;

    public DevoongException(HttpStatus status) {
        this.status = status;
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public DevoongException(String message) {
        this.message = message;
    }

    public DevoongException(HttpStatus status , String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }

    public DevoongException(HttpStatus status , int code , String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
