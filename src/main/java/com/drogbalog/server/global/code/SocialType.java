package com.drogbalog.server.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    GOOGLE("google"),
    NAVER("naver"),
    KAKAO("kakao"),
    FACEBOOK("facebook");


    private static final String prefix = "ROLE_";
    private final String name;
}
