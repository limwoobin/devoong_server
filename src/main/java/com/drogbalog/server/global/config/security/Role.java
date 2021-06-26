package com.drogbalog.server.global.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    GUEST("Guest"),
    USER("User"),
    ADMIN("Admin");

    private final String description;
}
