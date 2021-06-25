package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
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

    @InjectMocks
    private PostsService postsService;

    @Nested
    @DisplayName("게시글 목록 조회 테스트")
    class GetPostsTest extends PostsTestDomain {

        @Test
        @DisplayName("리스트가 빈값이면 빈값으로 반환되어야 한다.")
        public void getPostsListTest() {
            // given
            PageRequest pageRequest = PageRequest.of(0 , 5);

            // when
            when(postsRepository.findAll(pageRequest)).thenReturn(Page.empty());

            // then
            Page<PostsResponse> result = postsService.getPostsList(pageRequest);
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("임시 테스트")
        public void tempTest() {
            System.out.println("zzz");
        }
    }

}

class PostsTestDomain {
    final Posts 널_게시글 = null;
    final Posts posts = Posts.builder()
            .email("test")
            .build();
//    final Page<Posts> 빈_게시글_목록 = Page.empty();

    final PageRequest pageRequest = PageRequest.of(0 , 5);

    final Page<Posts> 게시글_목록 = new PageImpl<>(List.of(posts), pageRequest, 1);
}