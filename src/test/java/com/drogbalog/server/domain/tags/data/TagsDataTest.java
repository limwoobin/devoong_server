package com.drogbalog.server.domain.tags.data;

import com.drogbalog.server.domain.JpaTestConfig;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import com.drogbalog.server.global.code.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static com.drogbalog.server.domain.tags.data.TagsTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Import(JpaTestConfig.class)
public class TagsDataTest {
    @Autowired
    TagsRepository tagsRepository;

    @BeforeEach
    void init() {
        tagsRepository.save(tags);
        tagsRepository.save(tags2);
        tagsRepository.save(tags3);
        tagsRepository.save(tags4);
        tagsRepository.save(tags5);
    }

    @Test
    @DisplayName("ACTIVE 상태를 넘기면 ACTIVE 인 태그만 조회되어야 한다")
    void findTagsTest() {
        // given
        Status status = Status.ACTIVE;

        // then
        List<Tags> tagsList = tagsRepository.findAllByStatus(status);
        assertThat(tagsList.size()).isEqualTo(3);
        assertThat(tagsList.get(0).getName()).isEqualTo(tags.getName());
        assertThat(tagsList.get(1).getName()).isEqualTo(tags2.getName());
        assertThat(tagsList.get(2).getName()).isEqualTo(tags5.getName());
    }

    @Test
    @DisplayName("DISABLE 상태를 넘기면 DISABLE 인 태그만 조회되어야 한다")
    void findTagsTest2() {
        // given
        Status status = Status.DISABLE;

        // then
        List<Tags> tagsList = tagsRepository.findAllByStatus(status);
        assertThat(tagsList.size()).isEqualTo(2);
        assertThat(tagsList.get(0).getName()).isEqualTo(tags3.getName());
        assertThat(tagsList.get(1).getName()).isEqualTo(tags4.getName());
    }
}

final class TagsTestData {
    static final Tags tags = Tags.builder()
            .id(1L)
            .name("java-test")
            .status(Status.ACTIVE)
            .build();

    static final Tags tags2 = Tags.builder()
            .id(2L)
            .name("tdd-test")
            .status(Status.ACTIVE)
            .build();

    static final Tags tags3 = Tags.builder()
            .id(3L)
            .name("oop-test")
            .status(Status.DISABLE)
            .build();

    static final Tags tags4 = Tags.builder()
            .id(4L)
            .name("db-test")
            .status(Status.DISABLE)
            .build();

    static final Tags tags5 = Tags.builder()
            .id(5L)
            .name("solid-test")
            .status(Status.ACTIVE)
            .build();
}