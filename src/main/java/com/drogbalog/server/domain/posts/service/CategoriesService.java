package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.dao.CategoriesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesDao categoriesDao;


}
