package com.drogbalog.server.global.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonExceptionType implements ExceptionType {
    NOT_FOUND_USER("존재하지 않는 사용자입니다."),
    REFRESH_TOKEN_IS_EXPIRED("리프레시 토큰이 만료되었습니다."),
    INVALID_LOGIN_INFO("로그인 정보가 잘못되었습니다.");

    private final String message;
}
