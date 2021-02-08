package com.drogbalog.server.domain.posts.repository;

import com.drogbalog.server.domain.posts.domain.entity.Categories;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends DefaultRepository<Categories , Long> {
    List<Categories> findAllByStatus(Status status);
}
