package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.querydsl.PostsRepositorySupport;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsDao {
    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;
    private final PostsRepositorySupport postsRepositorySupport;

    @Transactional
    public Page<PostsResponse> findAll(PageRequest pageRequest) {
        Page<Posts> postsEntities = postsRepository.findAll(pageRequest);
        return postsEntities.map(postsMapper::toPostsResponse);
    }

    @Transactional
    public Page<PostsResponse> findAllByTagsId(PageRequest pageRequest , long tagsId) {
        Page<PostsResponse> postsResponsePage = postsRepositorySupport.findAllByTagsId(pageRequest , tagsId);
        return postsResponsePage;
    }

    @Transactional
    public PostsResponse findById(long postsId) {
        this.addPostsViews(postsId);
        return postsRepositorySupport.findById(postsId);
    }

    public void addPostsViews(long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));
        posts.addPostsViews();
        postsRepository.save(posts);
    }

    @Transactional
    public PostsResponse save(PostsRequest request) {
        Posts posts = Posts.builder()
                .email(request.getEmail())
                .subject(request.getSubject())
                .contents(request.getContents())
                .build();

        return postsMapper.toPostsResponse(postsRepository.save(posts));
    }

    @Transactional
    public PostsResponse update(PostsRequest request) {
        Posts posts = postsRepository.findById(request.getId())
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));

        posts.update(request.getSubject() , request.getContents());
        return postsMapper.toPostsResponse(posts);
    }

    @Transactional
    public Page<PostsResponse> searchAll(PageRequest pageRequest , String keyword) {
        return postsRepositorySupport.searchAllResponse(pageRequest , keyword);
    }
}
