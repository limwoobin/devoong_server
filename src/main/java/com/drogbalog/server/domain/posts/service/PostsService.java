package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.dao.PostsDao;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
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

    public Page<PostsResponse> getPostsListByTagsId(Pageable pageable , long tagsId) {
        return postsDao.findAllByTagsId(pageable , tagsId);
    }

    public PostsResponse getPosts(long postsId) {
        return postsDao.findById(postsId);
    }

    public Page<PostsResponse> searchAll(Pageable pageable , String keyword) {
        return postsDao.searchAll(pageable , keyword);
    }
}
