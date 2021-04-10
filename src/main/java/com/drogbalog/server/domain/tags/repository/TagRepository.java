package com.drogbalog.server.domain.tags.repository;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends DefaultRepository<Tags , Long> {
}
