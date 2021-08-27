package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.dto.Archive;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsCustomRepository {
    Page<PostsResponse> searchAllResponse(Pageable pageable, String keyword);

    List<PostsCard> findPreviousAndNextPostsCardById(Long id);

    List<Archive> findPostsArchive();

    Page<Posts> findAllPostsAndTags(Pageable pageable);
}
