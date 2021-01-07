package com.drogbalog.server.posts.repository;

import com.drogbalog.server.global.config.jpa.PagingAndSortRepository;
import com.drogbalog.server.posts.domain.entity.PostsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends PagingAndSortRepository<PostsEntity, Long> {
}
