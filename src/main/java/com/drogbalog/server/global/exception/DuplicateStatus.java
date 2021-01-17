package com.drogbalog.server.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DuplicateStatus {
    EMAIL_DUPLICATED(10001 , "Email is Duplicated"),
    NICKNAME_DUPLICATED(10002 , "NickName is Duplicated");

    private final int code;
    private final String message;
}
