package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.dto.CategoriesResponse;
import com.drogbalog.server.domain.posts.domain.entity.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesConverter {
    List<CategoriesResponse> categoriesConverts(List<Categories> categoriesList);

    CategoriesResponse categoryConverts(Categories categories);
}
