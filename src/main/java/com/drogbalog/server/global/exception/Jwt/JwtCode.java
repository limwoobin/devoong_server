package com.drogbalog.server.global.exception.Jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtCode {
    CLAIM(20001),
    EXPIRED(20002),
    MALFORMED(20003),
    PREMATURE(20004),
    SIGNATURE(20005),
    UNSUPPORTED(20006);

    private final int code;
}
