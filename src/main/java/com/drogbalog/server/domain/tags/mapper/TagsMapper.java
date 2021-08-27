package com.drogbalog.server.domain.tags.mapper;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagsMapper {
    TagsMapper INSTANCE = Mappers.getMapper(TagsMapper.class);

    List<TagsResponse> toTagResponseList(List<Tags> tags);

    TagsResponse toTagsResponse(Tags tags);
}
