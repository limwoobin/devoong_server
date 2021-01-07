package com.drogbalog.server.posts.dao;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.posts.converter.PostsConverter;
import com.drogbalog.server.posts.domain.dto.PostsDto;
import com.drogbalog.server.posts.domain.entity.PostsEntity;
import com.drogbalog.server.posts.domain.request.PostsRequest;
import com.drogbalog.server.posts.repository.PostsRepository;
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
    public Page<PostsDto> findAll(Pageable pageable) {
        Page<PostsEntity> postsEntities = repository.findAll(pageable);
        return null;
    }

    @Transactional
    public Page<PostsDto> findAllByPostsType(PostsType postsType) {
        Page<PostsEntity> postsEntities = repository.findAllByPostsType(postsType);
        return null;
    }

    @Transactional
    public PostsDto findById(long postsId) {
        PostsEntity postsEntity = repository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException("게시글을 찾을 수 없습니다."));

        return converter.convertEntity(postsEntity);
    }

    @Transactional
    public PostsDto save(PostsRequest request) {
        PostsEntity postsEntity = PostsEntity.builder()
                .email(request.getEmail())
                .subject(request.getSubject())
                .contents(request.getContents())
                .build();

        return converter.convertEntity(repository.save(postsEntity));
    }

    @Transactional
    public PostsDto update(PostsRequest request) {
        PostsEntity postsEntity = repository.findById(request.getId())
                .orElseThrow(() -> new EmptyDataException("게시글을 찾을 수 없습니다."));

        postsEntity.update(request.getSubject() , request.getContents());
        return converter.convertEntity(postsEntity);
    }
}
