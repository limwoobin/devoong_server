package com.drogbalog.server.global.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IllegalDataExceptionType implements ExceptionType {
    ILLEGAL_POSTS_CARD_DATA("잘못된 게시글 카드가 넘어왔습니다.");

    private final String message;
}
