package com.drogbalog.server.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvalidDataType {
    EMAIL(10000 , "invalid email"),
    PASSWORD(10001 , "invalid password");

    private int code;
    private String message;
}
