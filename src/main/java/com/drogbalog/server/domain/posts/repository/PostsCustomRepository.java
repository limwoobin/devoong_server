package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsCustomRepository {
    public Page<PostsResponse> searchAllResponse(Pageable pageable , String keyword);
}
