package com.drogbalog.server.global.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class DrogbalogException extends RuntimeException {
    private HttpStatus status;
    private int code;
    private String message;

    public DrogbalogException(HttpStatus status) {
        this.status = status;
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public DrogbalogException(String message) {
        this.message = message;
    }

    public DrogbalogException(int code , String message) {
        this.code = code;
        this.message = message;
    }

    public DrogbalogException(HttpStatus status , String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
