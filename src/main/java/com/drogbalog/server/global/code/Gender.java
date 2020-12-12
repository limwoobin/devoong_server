package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender implements CodeInfo {
    MALE("남성"),
    FEMALE("여성");

    private String description;
}
