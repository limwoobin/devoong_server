package com.drogbalog.server.comment.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "Like Api Request Model")
public class LikeRequestVo {

    @ApiModelProperty(value = "comment id")
    private long commentId;

    @ApiModelProperty(value = "child comment id")
    private long childCommentId;

    @ApiModelProperty(value = "user id")
    private long id;
}
