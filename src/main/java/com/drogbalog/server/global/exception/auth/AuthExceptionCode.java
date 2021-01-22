package com.drogbalog.server.global.exception.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthExceptionCode {
    CLAIM(20001),
    EXPIRED(20002),
    MALFORMED(20003),
    PREMATURE(20004),
    SIGNATURE(20005),
    UNSUPPORTED(20006),
    LOGOUT(20007),
    OAUTH2(20008);

    private final int code;
}
