package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType implements CodeInfo {
    JAVA("java"),
    SPRING("spring"),
    NETWORK("network"),
    NODEJS("nodejs");

    private String description;
}
