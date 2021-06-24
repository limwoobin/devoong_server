package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsTagsMappingRepository postsTagsMappingRepository;
    private final PostsMapper postsMapper = PostsMapper.INSTANCE;

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsList(Pageable pageable) {
        Page<Posts> posts = postsRepository.findAll(pageable);
        return posts.map(postsMapper::toPostsResponse);
    }

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsListByTagsId(Pageable pageable , Long tagsId) {
        return postsTagsMappingRepository.findAllByTagsId(pageable, tagsId);
    }

    @Transactional(readOnly = true)
    public PostsResponse getPosts(Long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));

        PostsResponse postsResponse = postsMapper.toPostsResponse(posts);
        postsResponse.addTagsList(postsTagsMappingRepository.findTagsByPostsId(postsId));
        return postsResponse;
    }

    @Transactional(readOnly = true)
    public Page<PostsResponse> searchAll(Pageable pageable , String keyword) {
        return postsRepository.searchAllResponse(pageable , keyword);
    }
}
