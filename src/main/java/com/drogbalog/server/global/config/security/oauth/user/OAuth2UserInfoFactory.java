package com.drogbalog.server.global.config.security.oauth.user;

import com.drogbalog.server.global.code.AuthProvider;
import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId , Map<String , Object> attributes) {
        if (registrationId.equals(AuthProvider.GOOGLE.getName())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equals(AuthProvider.NAVER.getName())) {
            return new NaverOAuth2UserInfo(attributes);
        } else if (registrationId.equals(AuthProvider.KAKAO.getName())) {
            return null;
        } else if (registrationId.equals(AuthProvider.FACEBOOK.getName())) {
            return null;
        } else {
            // todo: oauth2 exception 추가 예정
            return null;
        }
    }
}
