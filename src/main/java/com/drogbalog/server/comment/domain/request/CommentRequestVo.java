package com.drogbalog.server.comment.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "Comment Api Request Model")
public class CommentRequestVo {

    @ApiModelProperty(value = "comment id")
    private long commentId;

    @ApiModelProperty(value = "child comment id")
    private long childCommentId;

    @ApiModelProperty(value = "사용자 id")
    private long userId;

    @ApiModelProperty(value = "사용자 email")
    private String email;
}
