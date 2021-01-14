package com.drogbalog.server.domain.posts.domain.dto;

import com.drogbalog.server.global.code.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "카테고리 데이터 모델")
public class CategoriesResponse {

    @ApiModelProperty(value = "카테고리 id")
    private long id;

    @ApiModelProperty(value = "카테고리 이름")
    private String name;

    @ApiModelProperty(value = "카테고리 상태")
    private Status status;

    @ApiModelProperty(value = "카테고리 게시글")
    private List<PostsResponse> postsResponseList;
}
