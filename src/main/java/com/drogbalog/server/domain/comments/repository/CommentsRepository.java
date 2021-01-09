package com.drogbalog.server.domain.comments.repository;

import com.drogbalog.server.domain.comments.domain.entity.Comments;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends DefaultRepository<Comments, Long> {
    List<Comments> findAllByPostsIdOrderByIdDesc(long postsId);

    List<Comments> findAllByUserIdOrderByIdDesc(long userId);
}
