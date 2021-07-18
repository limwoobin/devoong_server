package com.drogbalog.server.domain.user.domain.request;

import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "User Api Request Model")
public class UserRequest {
    @ApiModelProperty(value = "사용자 id")
    private Long id;

    @NotNull
    @Email
    @ApiModelProperty(value = "사용자 이메일")
    private String email;

    @ApiModelProperty(value = "사용자 패스워드")
    private String password;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "프로필 이미지")
    private String imageUri;

    @Builder
    public UserRequest(String email , String password , String nickname , String imageUri) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imageUri = imageUri;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        String encryptPassword = this.password;
        this.password = passwordEncoder.encode(encryptPassword);
    }
}
