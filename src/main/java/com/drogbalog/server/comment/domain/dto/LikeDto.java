package com.drogbalog.server.comment.domain.dto;

import com.drogbalog.server.user.domain.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "좋아요 데이터 모델")
public class LikeDto {

    @ApiModelProperty(value = "좋아요 id")
    private long likeId;

    @ApiModelProperty(value = "사용자 데이터 모델")
    private UserDto userDto;
}
