package com.drogbalog.server.domain.user.domain.response;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "사용자 데이터 모델")
public class UserResponse {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "사용자 계정")
    private String email;

    @ApiModelProperty(value = "사용자 닉네임")
    private String nickname;

    @ApiModelProperty(value = "사용자 프로필 이미지")
    private String imageUri;

    @ApiModelProperty(value = "사용자 성별")
    private Gender gender;

    @ApiModelProperty(value = "사용자 상태")
    private Status status;

    @ApiModelProperty(value = "jwt token")
    private JwtResponse jwtResponse;

    public void initJwtToken(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
