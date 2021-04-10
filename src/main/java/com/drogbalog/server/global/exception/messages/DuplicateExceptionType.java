package com.drogbalog.server.global.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DuplicateExceptionType {
    EMAIL_DUPLICATED(10001 , "이미 사용중인 이메일입니다."),
    NICKNAME_DUPLICATED(10002 , "이미 사용중인 닉네임입니다."),
    ALREADY_EXISTS_SUBSCRIBER(10003 , "이미 구독한 이메일입니다."),
    NON_EXISTS_SUBSCRIBER(10004 , "존재하지 않는 이메일입니다.");


    private final int code;
    private final String message;
}
