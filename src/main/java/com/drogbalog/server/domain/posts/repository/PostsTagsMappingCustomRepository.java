package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsTagsMappingCustomRepository {
    Page<PostsResponse> findAllByTagsId(Pageable pageable , String name);
    List<TagsResponse> findTagsByPostsId(Long postsId);
}
