package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsTagsMappingRepository extends DefaultRepository<PostsTagsMapping , Long> {
}
