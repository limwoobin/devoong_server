package com.drogbalog.server.posts.service;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.posts.dao.PostsDao;
import com.drogbalog.server.posts.domain.dto.PostsDto;
import com.drogbalog.server.posts.domain.request.PostsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsDao postsDao;

    public Page<PostsDto> getPostsList(Pageable pageable) {
        return postsDao.findAll(pageable);
    }

    public Page<PostsDto> getPostsListByPostsType(PostsType postsType) {
        return postsDao.findAllByPostsType(postsType);
    }

    public PostsDto getPosts(long postsId) {
        return postsDao.findById(postsId);
    }

    public PostsDto createPosts(PostsRequest request) {
        return postsDao.save(request);
    }

    public PostsDto updatePosts(PostsRequest request) {
        return postsDao.update(request);
    }
}
