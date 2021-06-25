package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@DisplayName("게시글 조회 테스트")
@ExtendWith(MockitoExtension.class)
class PostsServiceTest {

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private PostsTagsMappingRepository postsTagsMappingRepository;

    @InjectMocks
    private PostsService postsService;

    @Nested
    @DisplayName("게시글 목록 조회 테스트")
    class GetPostsTest extends PostsTestDomain {

        @Test
        @DisplayName("리스트가 빈값이면 빈값으로 반환되어야 한다.")
        public void empty_getPostsListTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);

            // when
            when(postsRepository.findAll(pageRequest)).thenReturn(Page.empty());

            // then
            Page<PostsResponse> result = postsService.getPostsList(pageRequest);
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("리스트에 값이 있으면 정상적으로 반환되어야 한다.")
        public void getPostsListTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);
            Page<Posts> 게시글_목록 = new PageImpl<>(게시글_리스트 , pageRequest, 게시글_리스트.size());

            // when
            when(postsRepository.findAll(pageRequest)).thenReturn(게시글_목록);

            // then
            Page<PostsResponse> result = postsService.getPostsList(pageRequest);
            assertThat(result.getContent().size()).isEqualTo(4);
            assertThat(result.getContent().get(0).getId()).isEqualTo(1L);
            assertThat(result.getContent().get(1).getId()).isEqualTo(2L);
            assertThat(result.getContent().get(2).getId()).isEqualTo(3L);
            assertThat(result.getContent().get(3).getId()).isEqualTo(4L);
        }

        @Test
        @DisplayName("태그번호로 게시글 리스트 가져오기")
        public void getPostsListByTagsIdTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);

            // when
            when(postsTagsMappingRepository.findAllByTagsId(pageRequest , 1L)).thenReturn(Page.empty());

            // then
            Page<PostsResponse> result = postsService.getPostsListByTagsId(pageRequest , 1L);
            assertThat(result).isEqualTo(Page.empty());
        }
    }
}

class PostsTestDomain {
    final Posts 널_게시글 = null;
    final Posts posts = Posts.builder()
            .id(1L)
            .email("test")
            .build();

    final Posts posts2 = Posts.builder()
            .id(2L)
            .email("test2")
            .build();

    final Posts posts3 = Posts.builder()
            .id(3L)
            .email("test3")
            .build();

    final Posts posts4 = Posts.builder()
            .id(4L)
            .email("test4")
            .build();

    final Tags tags = Tags.builder()
            .id(1L)
            .name("tag1")
            .build();

    final Tags tags2 = Tags.builder()
            .id(2L)
            .name("tag2")
            .build();

    final PostsTagsMapping postsTagsMapping = PostsTagsMapping.builder()
            .id(1L)
            .posts(posts)
            .tags(tags)
            .build();

    final PostsTagsMapping postsTagsMapping2 = PostsTagsMapping.builder()
            .id(1L)
            .posts(posts2)
            .tags(tags)
            .build();

    final PostsTagsMapping postsTagsMapping3 = PostsTagsMapping.builder()
            .id(3L)
            .posts(posts3)
            .tags(tags)
            .build();


    final PageRequest pageRequest = PageRequest.of(0 , 5);

    final List<Posts> 게시글_리스트 = List.of(posts , posts2 , posts3 , posts4);
}