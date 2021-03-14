package com.drogbalog.server.domain.posts.domain.response;

import com.drogbalog.server.global.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "게시글 데이터 모델")
public class PostsResponse {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "subject")
    private String subject;

    @ApiModelProperty(value = "contents")
    private String contents;

    @ApiModelProperty(value = "views")
    private BigInteger views;

    @ApiModelProperty(value = "createdDate")
    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "modifiedDate")
    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;
}
