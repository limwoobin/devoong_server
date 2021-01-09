package com.drogbalog.server.domain.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "사용자 히스토리 모델")
public class UserHistoryDto {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "사용자 id")
    private long userId;

    @ApiModelProperty(value = "로그인 시간")
    private LocalDateTime loginAt;
}
