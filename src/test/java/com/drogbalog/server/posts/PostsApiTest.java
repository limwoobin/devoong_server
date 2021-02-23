package com.drogbalog.server.posts;

import com.drogbalog.server.domain.posts.api.PostsApi;
import com.drogbalog.server.domain.posts.service.PostsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;

@Log4j2
@WebMvcTest(PostsApi.class)
public class PostsApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsService postsService;

    @Test
    public void getSearchAll_tet() {
        // given
        final String keyword = "test";
        final PageRequest pageRequest = PageRequest.of(5 , 5);

        given(postsService.searchAll(keyword , pageRequest));

        // when

        // then
    }
}
