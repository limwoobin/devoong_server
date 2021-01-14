package com.drogbalog.server.domain.posts.domain.request;

import com.drogbalog.server.global.code.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Categories Request Model")
public class CategoriesRequest {

    @ApiModelProperty(value = "카테고리 id")
    private long id;

    @ApiModelProperty(value = "카테고리 이름")
    private String name;

    @ApiModelProperty(value = "카테고리 상태")
    private Status status;
}
