package com.drogbalog.server.domain.comments.domain.response;

import com.drogbalog.server.global.code.Status;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "댓글 모델")
public class CommentsResponse {
    private long id;

    private long postsId;

    private String email;

    private String contents;

    private Status status;
}
