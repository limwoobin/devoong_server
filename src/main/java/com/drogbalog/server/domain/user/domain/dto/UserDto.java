package com.drogbalog.server.domain.user.domain.dto;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.global.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "사용자 데이터 모델")
public class UserDto {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "사용자 계정")
    private String email;

    @ApiModelProperty(value = "사용자 닉네임")
    private String nickName;

    @ApiModelProperty(value = "사용자 프로필 이미지")
    private String profileImagePath;

    @ApiModelProperty(value = "사용자 성별")
    private Gender gender;

    @ApiModelProperty(value = "사용자 상태")
    private Status status;

    @ApiModelProperty(value = "access_token")
    private String accessToken;

    @ApiModelProperty(value = "refresh_token")
    private String refreshToken;
}
