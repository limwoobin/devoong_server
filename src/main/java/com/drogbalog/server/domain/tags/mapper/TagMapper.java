package com.drogbalog.server.domain.tags.mapper;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<TagsResponse> toTagResponseList(List<Tags> tags);
}
