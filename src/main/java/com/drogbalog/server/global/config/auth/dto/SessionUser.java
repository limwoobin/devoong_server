package com.drogbalog.server.global.config.auth.dto;

import com.drogbalog.server.user.domain.entity.UserEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String nickName;
    private String profileImagePath;

    public SessionUser(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.nickName = userEntity.getNickName();
        this.profileImagePath = userEntity.getProfileImagePath();
    }
}
