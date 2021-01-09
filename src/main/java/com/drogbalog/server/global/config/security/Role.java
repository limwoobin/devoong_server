package com.drogbalog.server.global.config.security;

import com.drogbalog.server.global.code.CodeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements CodeInfo {
    GUEST("Guest"),
    USER("User"),
    ADMIN("Admin");

    private String description;
}
