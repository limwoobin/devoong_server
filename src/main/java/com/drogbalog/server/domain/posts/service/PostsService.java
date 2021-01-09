package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.domain.posts.dao.PostsDao;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsDao postsDao;

    public Page<PostsResponse> getPostsList(Pageable pageable) {
        return postsDao.findAll(pageable);
    }

    public Page<PostsResponse> getPostsListByPostsType(PostsType postsType) {
        return postsDao.findAllByPostsType(postsType);
    }

    public PostsResponse getPosts(long postsId) {
        return postsDao.findById(postsId);
    }

    public PostsResponse createPosts(PostsRequest request) {
        return postsDao.save(request);
    }

    public PostsResponse updatePosts(PostsRequest request) {
        return postsDao.update(request);
    }
}