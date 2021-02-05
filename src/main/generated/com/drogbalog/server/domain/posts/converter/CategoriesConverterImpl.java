package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.dto.CategoriesResponse;
import com.drogbalog.server.domain.posts.domain.entity.Categories;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-05T15:21:52+0900",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class CategoriesConverterImpl implements CategoriesConverter {

    @Override
    public List<CategoriesResponse> categoriesConverts(List<Categories> categoriesList) {
        if ( categoriesList == null ) {
            return null;
        }

        List<CategoriesResponse> list = new ArrayList<CategoriesResponse>( categoriesList.size() );
        for ( Categories categories : categoriesList ) {
            list.add( categoryConverts( categories ) );
        }

        return list;
    }

    @Override
    public CategoriesResponse categoryConverts(Categories categories) {
        if ( categories == null ) {
            return null;
        }

        CategoriesResponse categoriesResponse = new CategoriesResponse();

        categoriesResponse.setId( categories.getId() );
        categoriesResponse.setName( categories.getName() );
        categoriesResponse.setStatus( categories.getStatus() );

        return categoriesResponse;
    }
}
