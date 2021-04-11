package com.drogbalog.server.domain.tags.mapper;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagsMapper {

    @Mapping(target = "postsTagsMappingList" , ignore = true)
    List<TagsResponse> toTagResponseList(List<Tags> tags);
}
