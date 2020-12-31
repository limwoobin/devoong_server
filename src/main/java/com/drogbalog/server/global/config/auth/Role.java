package com.drogbalog.server.global.config.auth;

import com.drogbalog.server.global.code.CodeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements CodeInfo {
    GUEST("Guest"),
    USER("User");

    private String description;
}
