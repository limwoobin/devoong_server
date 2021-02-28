package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.entity.Categories;
import com.drogbalog.server.domain.posts.domain.response.CategoriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesConverter {
    List<CategoriesResponse> categoriesConverts(List<Categories> categoriesList);

    @Mapping(target = "postsResponseList" , ignore = true)
    CategoriesResponse categoryConverts(Categories categories);
}
