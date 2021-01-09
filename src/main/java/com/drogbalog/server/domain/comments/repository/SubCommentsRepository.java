package com.drogbalog.server.domain.comments.repository;

import com.drogbalog.server.domain.comments.domain.entity.SubComments;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCommentsRepository extends DefaultRepository<SubComments , Long> {
}
