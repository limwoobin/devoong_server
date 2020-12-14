package com.drogbalog.server.global.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private int status;
    private String message;
    private Object data;
}
