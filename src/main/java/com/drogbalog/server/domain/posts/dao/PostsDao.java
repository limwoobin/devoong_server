package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.converter.PostsConverter;
import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsDao {
    private final PostsRepository repository;
    private final PostsConverter converter;

    @Transactional
    public Page<PostsResponse> findAll(Pageable pageable) {
        Page<Posts> postsEntities = repository.findAll(pageable);
        return postsEntities.map(converter::convertEntity);
    }

    @Transactional
    public Page<PostsResponse> findAllByCategoryId(long categoryId , Pageable pageable) {
        Page<Posts> postsEntities = repository.findAllByCategoryId(categoryId , pageable);
        return postsEntities.map(converter::convertEntity);
    }

    @Transactional
    public PostsResponse findById(long postsId) {
        Posts posts = repository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException("게시글을 찾을 수 없습니다."));
        this.addPostsViews(posts);

        return converter.convertEntity(posts);
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

        return converter.convertEntity(repository.save(posts));
    }

    @Transactional
    public PostsResponse update(PostsRequest request) {
        Posts Posts = repository.findById(request.getId())
                .orElseThrow(() -> new EmptyDataException("게시글을 찾을 수 없습니다."));

        Posts.update(request.getCategoryId() , request.getSubject() , request.getContents());
        return converter.convertEntity(Posts);
    }

    @Transactional
    public Page<PostsResponse> findAllByKeyword(String keyword , Pageable pageable) {

        return null;
    }
}
