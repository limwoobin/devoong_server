package com.drogbalog.server.global.config.security.oauth.user;

import com.drogbalog.server.global.code.AuthProvider;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import com.drogbalog.server.global.exception.auth.AuthExceptionCode;

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
            throw new UnAuthorizedException(AuthExceptionCode.OAUTH2.getCode() , "OAuth2 Authentication is failed.");
        }
    }
}
