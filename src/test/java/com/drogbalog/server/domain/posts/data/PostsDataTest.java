package com.drogbalog.server.domain.posts.data;

import com.drogbalog.server.domain.JpaTestConfig;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import com.drogbalog.server.global.code.Status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.drogbalog.server.domain.posts.data.PostsTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Import(JpaTestConfig.class)
public class PostsDataTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    PostsTagsMappingRepository postsTagsMappingRepository;

    void init() {
        postsRepository.save(posts);
        postsRepository.save(posts2);
        postsRepository.save(posts3);
        postsRepository.save(posts4);
        postsRepository.save(posts5);

        tagsRepository.save(tags);
        tagsRepository.save(tags2);
        tagsRepository.save(tags3);

        postsTagsMappingRepository.save(mapping);
        postsTagsMappingRepository.save(mapping2);
        postsTagsMappingRepository.save(mapping3);
        postsTagsMappingRepository.save(mapping4);
        postsTagsMappingRepository.save(mapping5);
        postsTagsMappingRepository.save(mapping6);
        postsTagsMappingRepository.save(mapping7);
        postsTagsMappingRepository.save(mapping8);
    }

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

            // then
            Posts savedPosts = postsRepository.save(posts);

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

            // then
            List<Posts> postsList = postsRepository.findTop3ByStatusOrderByIdDesc(status);

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
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(3L);

            assertThat(postsCards.size()).isEqualTo(3);
            assertThat(postsCards.get(0).getId()).isEqualTo(2L);
            assertThat(postsCards.get(1).getId()).isEqualTo(3L);
            assertThat(postsCards.get(2).getId()).isEqualTo(4L);
        }

        @Test
        @DisplayName("이전글이 없는 경우에 쿼리를 조회하면 (현재글|다음글)이 나와야한다")
        @SuppressWarnings("ResultOfMethodCallIgnored")
        void findTest3() {
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(1L);

            assertThat(postsCards.size()).isEqualTo(2);
            assertThat(postsCards.get(0).getId()).isEqualTo(1L);
            assertThat(postsCards.get(1).getId()).isEqualTo(2L);
            assertThatThrownBy(() -> {
                postsCards.get(2);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("다음글이 없는 경우에 쿼리를 조회하면 (이전글|현재글)이 나와야한다")
        @SuppressWarnings("ResultOfMethodCallIgnored")
        void findTest4() {
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(5L);

            assertThat(postsCards.size()).isEqualTo(2);
            assertThat(postsCards.get(0).getId()).isEqualTo(4L);
            assertThat(postsCards.get(1).getId()).isEqualTo(5L);
            assertThatThrownBy(() -> {
                postsCards.get(2);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }

        @Test
        @DisplayName("이전글 , 다음글 조회시 index 3 이상을 조회하면 IndexOutOfBoundsException 이 발생해야 한다")
        @SuppressWarnings("ResultOfMethodCallIgnored")
        void findTestException() {
            List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(3L);

            assertThatThrownBy(() -> {
                postsCards.get(3);
            }).isInstanceOf(IndexOutOfBoundsException.class);

            assertThatThrownBy(() -> {
                postsCards.get(4);
            }).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Nested
    @DisplayName("게시글 페이징 테스트")
    class PostsArchiveTest {
        @BeforeEach
        void init() {
            PostsDataTest.this.init();
        }

        @Test
        @DisplayName("데이터가 총 5개일때 페이징이 0,5 이면 모두 나와야한다")
        void findPagingTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);

            // then
            List<Posts> postsList = postsRepository.findAllPostsAndTags(pageRequest);
            assertThat(postsList.size()).isEqualTo(5);
            assertThat(postsList.get(0).getId()).isEqualTo(5L);
            assertThat(postsList.get(4).getId()).isEqualTo(1L);
        }

        @Test
        @DisplayName("데이터가 총 5개일때 페이징이 0,3 이면 모두 3개만 나와야한다")
        void findPagingTest2() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 3);

            // then
            List<Posts> postsList = postsRepository.findAllPostsAndTags(pageRequest);
            assertThat(postsList.size()).isEqualTo(3);
            assertThat(postsList.get(0).getId()).isEqualTo(5L);
            assertThat(postsList.get(2).getId()).isEqualTo(3L);
        }

        @Test
        @DisplayName("데이터가 총 5개일때 페이징이 1,2 이면 모두 두번째페이지의 두개가 나와야한다")
        void findPagingTest3() {
            // given
            PageRequest pageRequest = PageRequest.of(1 , 2);

            // then
            List<Posts> postsList = postsRepository.findAllPostsAndTags(pageRequest);
            assertThat(postsList.size()).isEqualTo(2);
            assertThat(postsList.get(0).getId()).isEqualTo(3L);
            assertThat(postsList.get(1).getId()).isEqualTo(2L);
        }

        @Test
        @DisplayName("데이터가 총 5개일때 페이징이 2,2 이면 모두 세번째 페이지의 나머지 데이터가 나와야한다")
        void findPagingTest4() {
            // given
            PageRequest pageRequest = PageRequest.of(2 , 2);

            // then
            List<Posts> postsList = postsRepository.findAllPostsAndTags(pageRequest);
            assertThat(postsList.size()).isEqualTo(1);
            assertThat(postsList.get(0).getId()).isEqualTo(1L);
        }

        @Test
        @DisplayName("총 게시글의 수를 조회하면 정상적으로 조회되어야 한다")
        void findAllCount() {
            long count = postsRepository.findAllPostsCount();

            assertThat(count).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("게시글-태그 매핑 테스트")
    class PostsTagsMappingTest {
        @BeforeEach
        public void init() {
            PostsDataTest.this.init();
        }

        @Test
        @DisplayName("태그명으로 페이징 게시글 조회시 정상적으로 조회되어야한다")
        void findPostsAndTagsTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);
            String tagName = tags2.getName();

            // then
            Page<PostsResponse> postsResponsePage = postsTagsMappingRepository.findAllByTagsName(pageRequest , tagName);
            assertThat(postsResponsePage.getTotalElements()).isEqualTo(2);
        }

        @Test
        @DisplayName("태그명으로 페이징 게시글 조회시 정상적으로 정렬되어야한다")
        void findPostsAndTagsTest2() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);
            String tagName = tags2.getName();

            // then
            Page<PostsResponse> postsResponsePage = postsTagsMappingRepository.findAllByTagsName(pageRequest , tagName);
            assertThat(postsResponsePage.getTotalElements()).isEqualTo(2);

            List<PostsResponse> postsResponseList = postsResponsePage.getContent();
            assertThat(postsResponseList.get(0).getId()).isEqualTo(3L);
            assertThat(postsResponseList.get(1).getId()).isEqualTo(1L);
            assertThat(postsResponseList.get(0).getId()).isGreaterThan(postsResponseList.get(1).getId());
        }

        @Test
        @DisplayName("태그명으로 페이징 게시글 조회시 페이징이 정상적으로 되어야한다")
        void findPostsAndTagsTest3() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 2);
            String tagName = tags3.getName();

            // then
            Page<PostsResponse> postsResponsePage = postsTagsMappingRepository.findAllByTagsName(pageRequest , tagName);
            assertThat(postsResponsePage.getTotalElements()).isEqualTo(4);
            assertThat(postsResponsePage.getSize()).isEqualTo(2);
            assertThat(postsResponsePage.getTotalPages()).isEqualTo(2);
        }

        @Test
        @DisplayName("태그명으로 페이징 게시글 조회시 페이징이 정상적으로 되어야한다_v2")
        void findPostsAndTagsTest4() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 3);
            String tagName = tags3.getName();

            // then
            Page<PostsResponse> postsResponsePage = postsTagsMappingRepository.findAllByTagsName(pageRequest , tagName);
            assertThat(postsResponsePage.getTotalElements()).isEqualTo(4);
            assertThat(postsResponsePage.getSize()).isEqualTo(3);
            assertThat(postsResponsePage.getTotalPages()).isEqualTo(2);
        }

        @Test
        @DisplayName("태그명으로 페이징 게시글 조회시 페이징이 정상적으로 되어야한다_v3")
        void findPostsAndTagsTest5() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 1);
            String tagName = tags3.getName();

            // then
            Page<PostsResponse> postsResponsePage = postsTagsMappingRepository.findAllByTagsName(pageRequest , tagName);
            assertThat(postsResponsePage.getTotalElements()).isEqualTo(4);
            assertThat(postsResponsePage.getSize()).isEqualTo(1);
            assertThat(postsResponsePage.getTotalPages()).isEqualTo(4);
        }

        @Test
        @DisplayName("게시글 id 로 태그명 조회시 정상적으로 조회되어야한다")
        void findTagsByPostsIdTest() {
            // given
            long postsId = posts2.getId();

            // then
            List<TagsResponse> tagsResponseList = postsTagsMappingRepository.findTagsByPostsId(postsId);
            assertThat(tagsResponseList.size()).isEqualTo(1);
            assertThat(tagsResponseList.get(0).getName()).isEqualTo(tags3.getName());
        }

        @Test
        @DisplayName("게시글 id 로 태그명 조회시 정상적으로 조회되어야한다")
        void findTagsByPostsIdTest_v2() {
            // given
            long postsId = posts3.getId();

            // then
            List<TagsResponse> tagsResponseList = postsTagsMappingRepository.findTagsByPostsId(postsId);
            assertThat(tagsResponseList.size()).isEqualTo(2);
            assertThat(tagsResponseList.get(0).getName()).isEqualTo(tags2.getName());
            assertThat(tagsResponseList.get(1).getName()).isEqualTo(tags3.getName());
        }
    }
}

final class PostsTestData {
    static final Posts posts = Posts.builder()
            .id(1L)
            .email("test-email")
            .title("test-title")
            .contents("test-contents")
            .postsTagsMappingList(Collections.emptyList())
            .createdDate(LocalDateTime.of(2021, 12, 19, 5, 11 , 0))
            .build();

    static final Posts posts2 = Posts.builder()
            .id(2L)
            .email("test-email-2")
            .title("test-title-2")
            .contents("test-contents-2")
            .postsTagsMappingList(Collections.emptyList())
            .createdDate(LocalDateTime.of(2021, 7, 11, 14, 25 , 0))
            .build();

    static final Posts posts3 = Posts.builder()
            .id(3L)
            .email("test-email-3")
            .title("test-title-3")
            .contents("test-contents-3")
            .postsTagsMappingList(Collections.emptyList())
            .createdDate(LocalDateTime.of(2021, 7, 15, 1, 5 , 0))
            .build();

    static final Posts posts4 = Posts.builder()
            .id(4L)
            .email("test-email-4")
            .title("test-title-4")
            .contents("test-contents-4")
            .postsTagsMappingList(Collections.emptyList())
            .createdDate(LocalDateTime.of(2021, 6, 29, 23, 17 , 0))
            .build();

    static final Posts posts5 = Posts.builder()
            .id(5L)
            .email("test-email-5")
            .title("test-title-5")
            .contents("test-contents-5")
            .postsTagsMappingList(Collections.emptyList())
            .createdDate(LocalDateTime.of(2021, 10, 28, 4, 30 , 0))
            .build();

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
            .status(Status.ACTIVE)
            .build();

    static final PostsTagsMapping mapping = PostsTagsMapping
            .builder()
            .id(1L)
            .posts(posts)
            .tags(tags)
            .build();

    static final PostsTagsMapping mapping2 = PostsTagsMapping
            .builder()
            .id(2L)
            .posts(posts)
            .tags(tags2)
            .build();

    static final PostsTagsMapping mapping3 = PostsTagsMapping
            .builder()
            .id(3L)
            .posts(posts2)
            .tags(tags3)
            .build();

    static final PostsTagsMapping mapping4 = PostsTagsMapping
            .builder()
            .id(4L)
            .posts(posts3)
            .tags(tags2)
            .build();

    static final PostsTagsMapping mapping5 = PostsTagsMapping
            .builder()
            .id(5L)
            .posts(posts4)
            .tags(tags)
            .build();

    static final PostsTagsMapping mapping6 = PostsTagsMapping
            .builder()
            .id(6L)
            .posts(posts5)
            .tags(tags3)
            .build();

    static final PostsTagsMapping mapping7 = PostsTagsMapping
            .builder()
            .id(7L)
            .posts(posts3)
            .tags(tags3)
            .build();

    static final PostsTagsMapping mapping8 = PostsTagsMapping
            .builder()
            .id(8L)
            .posts(posts4)
            .tags(tags3)
            .build();
}
