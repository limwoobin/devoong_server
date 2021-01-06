package com.drogbalog.server.global.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private int code;
    private HttpStatus status;
    private String message;
}
