package com.drogbalog.server.posts.converter;

import com.drogbalog.server.posts.domain.dto.PostsDto;
import com.drogbalog.server.posts.domain.entity.PostsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsConverter {

    PostsDto convertEntity(PostsEntity postsEntity);
}
