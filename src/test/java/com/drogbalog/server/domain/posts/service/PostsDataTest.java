package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.JpaTestConfig;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.global.code.Status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;

import static com.drogbalog.server.domain.posts.service.DataJpaTestDomain.*;
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
    class PostsSaveTest {
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

    @Nested
    @DisplayName("Posts 조회 테스트")
    class PostsFindTest {

        @BeforeEach
        void init() {
            postsRepository.save(posts);
            postsRepository.save(posts2);
            postsRepository.save(posts3);
            postsRepository.save(posts4);
            postsRepository.save(posts5);
        }

        @Test
        @DisplayName("최근 게시글을 조회하면 최근 3건의 게시글이 나와야한다")
        void findTest() {
            // given
            Status status = Status.ACTIVE;

            // when
            List<Posts> postsList = postsRepository.findTop3ByStatusOrderByIdDesc(status);

            // then
            assertThat(postsList.size()).isEqualTo(3);
            assertThat(postsList.get(0).getId()).isEqualTo(5L);
            assertThat(postsList.get(1).getId()).isEqualTo(4L);
            assertThat(postsList.get(2).getId()).isEqualTo(3L);
        }

        @Test
        @DisplayName("모든 게시글을 조회하면 모두 나와야한다")
        void findAllTest() {
            List<Posts> postsList = postsRepository.findAll();

            assertThat(postsList.size()).isEqualTo(5);
        }

        @Test
        @DisplayName("이전글 , 다음글을이 있는 경우에 쿼리를 조회하면 (이전글|현재글|다음글)이 나와야한다")
        void findTest2() {
            // when
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(3L);

            // then
            assertThat(postsCards.size()).isEqualTo(3);
            assertThat(postsCards.get(0).getId()).isEqualTo(2L);
            assertThat(postsCards.get(1).getId()).isEqualTo(3L);
            assertThat(postsCards.get(2).getId()).isEqualTo(4L);
        }

        @Test
        @DisplayName("이전글이 없는 경우에 쿼리를 조회하면 (현재글|다음글)이 나와야한다")
        void findTest3() {
            // when
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(1L);

            // then
            assertThat(postsCards.size()).isEqualTo(2);
            assertThat(postsCards.get(0).getId()).isEqualTo(1L);
            assertThat(postsCards.get(1).getId()).isEqualTo(2L);
            assertThatThrownBy(() -> {
                postsCards.get(2);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("다음글이 없는 경우에 쿼리를 조회하면 (이전글|현재글)이 나와야한다")
        void findTest4() {
            // when
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(5L);

            // then
            assertThat(postsCards.size()).isEqualTo(2);
            assertThat(postsCards.get(0).getId()).isEqualTo(4L);
            assertThat(postsCards.get(1).getId()).isEqualTo(5L);
            assertThatThrownBy(() -> {
                postsCards.get(2);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("이전글 , 다음글 조회시 index 3 이상을 조회하면 IndexOutOfBoundsException 이 발생해야 한다")
        void findTestException() {
            // when
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(3L);

            // then
            assertThatThrownBy(() -> {
                postsCards.get(3);
            }).isInstanceOf(IndexOutOfBoundsException.class);

            assertThatThrownBy(() -> {
                postsCards.get(4);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }
}

final class DataJpaTestDomain {
    static final Posts posts = Posts.builder()
            .id(1L)
            .email("test-email")
            .title("test-title")
            .contents("test-contents")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts2 = Posts.builder()
            .id(2L)
            .email("test-email-2")
            .title("test-title-2")
            .contents("test-contents-2")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts3 = Posts.builder()
            .id(3L)
            .email("test-email-3")
            .title("test-title-3")
            .contents("test-contents-3")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts4 = Posts.builder()
            .id(4L)
            .email("test-email-4")
            .title("test-title-4")
            .contents("test-contents-4")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts5 = Posts.builder()
            .id(5L)
            .email("test-email-5")
            .title("test-title-5")
            .contents("test-contents-5")
            .postsTagsMappingList(Collections.emptyList())
            .build();
}
