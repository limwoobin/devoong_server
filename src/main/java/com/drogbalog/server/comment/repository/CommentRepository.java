package com.drogbalog.server.comment.repository;

import com.drogbalog.server.comment.domain.entity.CommentEntity;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends DefaultRepository<CommentEntity , Long> {
}
