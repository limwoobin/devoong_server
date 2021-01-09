package com.drogbalog.server.domain.user.domain.request;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "User Api Request Model")
public class UserRequest {
    public UserRequest() {
        this.status = Status.ACTIVE;
        this.role = Role.USER;
    }

    @ApiModelProperty(value = "사용자 id")
    private long id;

    @NotNull
    @Email
    @ApiModelProperty(value = "사용자 이메일")
    private String email;

    @ApiModelProperty(value = "사용자 패스워드")
    private String password;

    @ApiModelProperty(value = "닉네임")
    private String nickName;

    @ApiModelProperty(value = "프로필 이미지")
    private String profileImagePath;

    @ApiModelProperty(value = "성별")
    private Gender gender;

    @ApiModelProperty(value = "사용자 상태")
    private Status status;

    @ApiModelProperty(value = "사용자 구분")
    private Role role;
}
