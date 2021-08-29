package com.drogbalog.server.domain.posts.mapper;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    PostsMapper INSTANCE = Mappers.getMapper(PostsMapper.class);



    @Mappings({
            @Mapping(target = "tagsResponseList", ignore = true),
            @Mapping(target = "previousPostsCard", ignore = true),
            @Mapping(target = "nextPostsCard", ignore = true)
    })
    PostsResponse converts(Posts posts);

    List<PostsResponse> convertList(List<Posts> postsList);
}
