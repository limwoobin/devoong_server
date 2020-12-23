package com.drogbalog.server.user.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "User Api Request Model")
public class UserRequestVo {

    @ApiModelProperty(value = "사용자 id")
    private long id;

    @ApiModelProperty(value = "사용자 이메일")
    private String email;

    @ApiModelProperty(value = "사용자 패스워드")
    private String password;
}
