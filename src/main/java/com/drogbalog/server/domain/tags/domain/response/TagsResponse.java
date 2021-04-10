package com.drogbalog.server.domain.tags.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Tags 응답 모델")
public class TagsResponse {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "태그 이름")
    private String name;
}
