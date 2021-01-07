package com.drogbalog.server.posts.service;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.posts.dao.PostsDao;
import com.drogbalog.server.posts.domain.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsDao postsDao;

    public List<PostsDto> getPostsList() {
        return null;
    }

    public List<PostsDto> getPostsListByPostsType(PostsType postsType) {

        return null;
    }
}
