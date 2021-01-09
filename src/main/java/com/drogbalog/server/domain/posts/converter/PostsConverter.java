package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsConverter {
    PostsResponse convertEntity(Posts postsEntity);
}
