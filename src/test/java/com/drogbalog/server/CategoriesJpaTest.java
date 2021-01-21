package com.drogbalog.server;

import com.drogbalog.server.domain.posts.domain.entity.Categories;
import com.drogbalog.server.domain.posts.repository.CategoriesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaAuditing
public class CategoriesJpaTest {

    @Autowired
    CategoriesRepository repository;

    @Test
    public void test() {
        Categories categories = Categories.builder()
                .name("zz")
                .build();
    }
}
