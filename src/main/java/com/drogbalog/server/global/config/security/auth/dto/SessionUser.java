package com.drogbalog.server.global.config.security.auth.dto;

import com.drogbalog.server.domain.user.domain.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String nickName;
    private String profileImagePath;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.profileImagePath = user.getProfileImagePath();
    }
}
