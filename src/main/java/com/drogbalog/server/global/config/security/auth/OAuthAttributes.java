package com.drogbalog.server.global.config.security.auth;

import com.drogbalog.server.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String , Object> attributes;
    private String emailAttributeKey;
    private String email;
    private String nickName;
    private String profileImagePath;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String emailAttributeKey, String email,
                           String nickName, String profileImagePath) {
        this.attributes = attributes;
        this.emailAttributeKey = emailAttributeKey;
        this.email = email;
        this.nickName = nickName;
        this.profileImagePath = profileImagePath;
    }

    public static OAuthAttributes of(String registrationId,
                                      String userNameAttributeName,
                                      Map<String , Object> attributes) {
        return ofGoogle(userNameAttributeName , attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String , Object> attributes) {
        return OAuthAttributes.builder()
                .email(String.valueOf(attributes.get("email")))
                .nickName(String.valueOf(attributes.get("nickName")))
                .profileImagePath(String.valueOf(attributes.get("profileImagePath")))
                .attributes(attributes)
                .emailAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                    .email(email)
                    .nickName(nickName)
                    .profileImagePath(profileImagePath)
                    .build();
    }
}
