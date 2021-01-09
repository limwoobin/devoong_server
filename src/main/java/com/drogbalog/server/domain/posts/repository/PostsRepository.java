package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import com.drogbalog.server.domain.posts.domain.entity.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends DefaultRepository<PostsEntity, Long> {
    Page<PostsEntity> findAllByPostsType(PostsType postsType);
}
