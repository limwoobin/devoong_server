package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.converter.CategoriesConverter;
import com.drogbalog.server.domain.posts.domain.dto.CategoriesResponse;
import com.drogbalog.server.domain.posts.domain.entity.Categories;
import com.drogbalog.server.domain.posts.domain.request.CategoriesRequest;
import com.drogbalog.server.domain.posts.repository.CategoriesRepository;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.EmptyDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesDao {
    private final CategoriesRepository repository;
    private final CategoriesConverter converter;

    @Transactional
    public CategoriesResponse saveCategory(String category) {
        Categories categories = Categories.builder()
                .name(category)
                .build();

        return converter.categoryConverts(repository.save(categories));
    }

    @Transactional
    public CategoriesResponse updateCategory(CategoriesRequest request) {
        Categories categories = repository.findById(request.getId())
                .orElseThrow(() -> new EmptyDataException("No Such CategoryId"));

        categories.update(request.getName() , request.getStatus());
        return converter.categoryConverts(categories);
    }

    @Transactional
    public void deleteCategory(long categoryId) {
        repository.deleteById(categoryId);
    }

    @Transactional
    public List<CategoriesResponse> getCategories() {
        List<Categories> categories = repository.findAllByStatus(Status.ACTIVE);
        return converter.categoriesConverts(categories);
    }
}
