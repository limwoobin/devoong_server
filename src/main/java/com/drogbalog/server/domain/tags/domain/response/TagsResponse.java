package com.drogbalog.server.domain.tags.domain.response;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Tags 응답 모델")
public class TagsResponse {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "태그 이름")
    private String name;

    public TagsResponse(Long id , String name) {
        this.id = id;
        this.name = name;
    }

    public TagsResponse toTagsResponse(Tags tags) {
        this.id = tags.getId();
        this.name = tags.getName();
        return this;
    }
}
