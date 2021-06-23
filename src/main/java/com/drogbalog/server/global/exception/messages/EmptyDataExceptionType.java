package com.drogbalog.server.global.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmptyDataExceptionType implements ExceptionType {
    EMPTY_POSTS_DATA(30001 , "게시글을 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
