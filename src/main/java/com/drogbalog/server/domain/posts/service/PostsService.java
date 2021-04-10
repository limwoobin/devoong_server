package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.dao.PostsDao;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsDao postsDao;

    public Page<PostsResponse> getPostsList(PageRequest pageRequest) {
        return postsDao.findAll(pageRequest);
    }

    public Page<PostsResponse> getPostsListByTagsId(PageRequest pageRequest , long tagsId) {
        return postsDao.findAllByTagsId(pageRequest , tagsId);
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

    public Page<PostsResponse> searchAll(PageRequest pageRequest , String keyword) {
        return postsDao.searchAll(pageRequest , keyword);
    }
}
