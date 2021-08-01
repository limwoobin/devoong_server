package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsCustomRepository {
    public Page<PostsResponse> searchAllResponse(Pageable pageable , String keyword);

    public List<PostsCard> findPreviousAndNextPostsCardById(Long id);
}
