package com.drogbalog.server.domain.tags.repository;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends DefaultRepository<Tags , Long> {
    List<Tags> findAllByStatus(Status status);
}
