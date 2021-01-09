package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.dto.PostsDto;
import com.drogbalog.server.domain.posts.domain.entity.PostsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsConverter {
    PostsDto convertEntity(PostsEntity postsEntity);
}
