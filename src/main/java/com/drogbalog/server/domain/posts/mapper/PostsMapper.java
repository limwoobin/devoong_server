package com.drogbalog.server.domain.posts.mapper;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    @Mapping(target = "tagsResponseList" , ignore = true)
    PostsResponse toPostsResponse(Posts posts);
}
