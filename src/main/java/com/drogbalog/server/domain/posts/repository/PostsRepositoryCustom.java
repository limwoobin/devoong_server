package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsRepositoryCustom {
    public Page<PostsResponse> searchAllResponse(Pageable pageable , String keyword);

    public Page<PostsResponse> findAllByTagsId(Pageable pageable , Long tagsId);
}