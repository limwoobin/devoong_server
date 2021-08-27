package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends DefaultRepository<Posts, Long> , PostsCustomRepository {
    List<Posts> findTop3ByStatusOrderByIdDesc(Status status);
}
