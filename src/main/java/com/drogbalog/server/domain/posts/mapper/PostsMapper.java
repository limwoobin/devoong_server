package com.drogbalog.server.domain.posts.mapper;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostsMapper {

    PostsResponse converts(Posts posts);

    default PostsResponse toPostsResponse(Posts posts) {
        PostsResponse postsResponse = this.converts(posts);
        List<TagsResponse> tagsResponseList = posts.getPostsTagsMappingList().stream()
                .map(entity -> new TagsResponse().toTagsResponse(entity.getTags()))
                .collect(Collectors.toList());

        postsResponse.addTagsList(tagsResponseList);
        return postsResponse;
    }
}
