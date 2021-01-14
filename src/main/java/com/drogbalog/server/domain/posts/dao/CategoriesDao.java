package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesDao {
    private final CategoriesRepository repository;

}
