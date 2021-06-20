package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends DefaultRepository<Posts, Long> , PostsRepositoryCustom {

}
