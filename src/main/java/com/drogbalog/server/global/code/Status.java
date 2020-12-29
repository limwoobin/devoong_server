package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status implements CodeInfo {
    ACTIVE("active"),
    DISABLE("disable");

    private String description;
}
