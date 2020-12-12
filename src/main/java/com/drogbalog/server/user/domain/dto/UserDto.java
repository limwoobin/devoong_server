package com.drogbalog.server.user.domain.dto;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.util.DateTimeUtil;
import com.drogbalog.server.user.domain.entity.UserHistoryEntity;
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
    private String userEmail;

    @ApiModelProperty(value = "사용자 닉네임")
    private String nickName;

    @ApiModelProperty(value = "사용자 프로필 이미지")
    private String profileImagePath;

    @ApiModelProperty(value = "사용자 성별")
    private Gender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "사용자 히스토리")
    private UserHistoryDto userHistory;
}
