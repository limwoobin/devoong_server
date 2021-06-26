package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE("active" , "ACTIVE"),
    DISABLE("disable" , "DISABLE");

    private final String description;
    private final String strValue;
}
