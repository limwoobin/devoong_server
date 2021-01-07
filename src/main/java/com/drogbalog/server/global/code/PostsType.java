package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostsType implements CodeInfo {
    JAVA("java"),
    SPRING("spring"),
    NETWORK("network"),
    NODEJS("nodejs");

    private String description;
}
