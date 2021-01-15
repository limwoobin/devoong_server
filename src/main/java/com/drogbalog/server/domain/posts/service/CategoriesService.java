package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.dao.CategoriesDao;
import com.drogbalog.server.domain.posts.domain.dto.CategoriesResponse;
import com.drogbalog.server.domain.posts.domain.request.CategoriesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesDao categoriesDao;

    public CategoriesResponse saveCategory(String category) {
        return categoriesDao.saveCategory(category);
    }

    public CategoriesResponse updateCategory(CategoriesRequest request) {
        return categoriesDao.updateCategory(request);
    }

    public void deleteCategory(long categoryId) {
        categoriesDao.deleteCategory(categoryId);
    }

    public List<CategoriesResponse> getCategories() {
        return categoriesDao.getCategories();
    }
}
