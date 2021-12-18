package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.JpaTestConfig;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Import(JpaTestConfig.class)
public class PostsDataTest {

    @Autowired
    PostsRepository postsRepository;

    @Nested
    @DisplayName("save 관련 테스트")
    class SaveTest {

        @Test
        @DisplayName("엔티티 저장시 정상적으로 저장되어야 한다")
        void saveTest() {
            // given
            Posts posts = Posts.builder()
                    .id(1L)
                    .email("test@gmail.com")
                    .title("test-title")
                    .contents("test-contents")
                    .build();

            // when
            Posts savedPosts = postsRepository.save(posts);

            // then
            assertThat(savedPosts).isNotNull();
            assertThat(savedPosts.getId()).isEqualTo(1L);
            assertThat(savedPosts.getEmail()).isEqualTo("test@gmail.com");
            assertThat(savedPosts.getTitle()).isEqualTo("test-title");
            assertThat(savedPosts.getContents()).isEqualTo("test-contents");
        }

        @Test
        @DisplayName("엔티티 저장시 필수값이 빠져있으면 DataIntegrityViolationException 이 발생해야 한다")
        void saveTest2() {
            // given
            Posts posts = Posts.builder()
                    .id(1L)
                    .email("test@gmail.com")
                    .title("test-title")
                    .build();

            // then
            assertThatThrownBy(() -> {
                postsRepository.save(posts);
            }).isInstanceOf(DataIntegrityViolationException.class);
        }
    }


    @Test
    void findTest() {
        List<Posts> postsList = postsRepository.findAll();
        assertThat(postsList.size()).isEqualTo(0);
    }
}
