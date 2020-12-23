package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UseType implements CodeInfo {
    ACTIVE("활성화"),
    DISABLE("비활성화");

    private String description;
}
