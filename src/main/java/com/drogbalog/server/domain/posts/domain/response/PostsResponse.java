package com.drogbalog.server.domain.posts.domain.response;

import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.global.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "게시글 데이터 모델")
public class PostsResponse {

    @ApiModelProperty(value = "id")
    private long id;

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

    @ApiModelProperty(value = "tags")
    private List<TagsResponse> tagsResponseList;

    public void addTagsList(List<TagsResponse> tagsResponseList) {
        this.tagsResponseList = tagsResponseList;
    }

    public PostsResponse(long id , String subject , String contents , LocalDateTime createdDate) {
        this.id = id;
        this.subject = subject;
        this.contents = contents;
        this.createdDate = createdDate;
    }
}
