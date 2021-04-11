package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsDao {
    private final PostsRepository repository;
    private final PostsMapper postsMapper;
    private final PostsRepositorySupport postsRepositorySupport;
    private final PostsTagsMappingRepository postsTagsMappingRepository;

    @Transactional
    public Page<PostsResponse> findAll(PageRequest pageRequest) {
        Page<Posts> postsEntities = repository.findAll(pageRequest);
        return postsEntities.map(postsMapper::toPostsResponse);
    }

    @Transactional
    public Page<PostsResponse> findAllByTagsId(PageRequest pageRequest , long tagsId) {
        List<PostsTagsMapping> postsTagsMappingList = postsTagsMappingRepository.findAllByTagsId(tagsId);
        List<Long> postsIds = postsTagsMappingList
                .stream()
                .map(PostsTagsMapping::getPostsId)
                .collect(Collectors.toList());

        return postsRepositorySupport.findAllByTagsId(pageRequest, postsIds);
    }

    @Transactional
    public PostsResponse findById(long postsId) {
        Posts posts = repository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));
        this.addPostsViews(posts);

        return postsMapper.toPostsResponse(posts);
    }

    public void addPostsViews(Posts posts) {
        posts.addPostsViews();
        repository.save(posts);
    }

    @Transactional
    public PostsResponse save(PostsRequest request) {
        Posts posts = Posts.builder()
                .email(request.getEmail())
                .subject(request.getSubject())
                .contents(request.getContents())
                .build();

        return postsMapper.toPostsResponse(repository.save(posts));
    }

    @Transactional
    public PostsResponse update(PostsRequest request) {
        Posts posts = repository.findById(request.getId())
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));

        posts.update(request.getSubject() , request.getContents());
        return postsMapper.toPostsResponse(posts);
    }

    @Transactional
    public Page<PostsResponse> searchAll(PageRequest pageRequest , String keyword) {
        return postsRepositorySupport.searchAllResponse(pageRequest , keyword);
    }
}
