package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsConverter {
    PostsResponse convertEntity(Posts postsEntity);
}
