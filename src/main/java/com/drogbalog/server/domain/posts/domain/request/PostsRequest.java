package com.drogbalog.server.domain.posts.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@ApiModel(value = "Posts Request Model")
public class PostsRequest {

    @ApiModelProperty(value = "게시글 id")
    private long id;

    @ApiModelProperty(value = "작성자")
    private String email;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String contents;

    @ApiModelProperty(value = "page")
    private Pageable pageable;
}
