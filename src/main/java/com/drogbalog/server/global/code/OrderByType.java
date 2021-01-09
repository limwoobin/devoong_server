package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderByType implements CodeInfo {
    ID("id"),
    CREATED_DATE("createdDate"),
    MODIFIED_DATE("modifiedDate");

    private final String description;
}
