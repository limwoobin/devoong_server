package com.drogbalog.server.domain.comments.repository;

import com.drogbalog.server.domain.comments.domain.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
