package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.entity.Categories;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends DefaultRepository<Categories , Long> {
}
