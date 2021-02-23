package com.drogbalog.server.posts;

import com.drogbalog.server.domain.posts.api.PostsApi;
import com.drogbalog.server.domain.posts.service.PostsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Log4j2
@WebMvcTest(controllers = {PostsApi.class} , useDefaultFilters = false)
public class PostsApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsService postsService;

    @Test
    public void getSearchAll_test() throws Exception {
        log.info("PostsApi Controller Test");

        // given
        final String keyword = "test";
        final PageRequest pageRequest = PageRequest.of(5 , 5);

        given(postsService.searchAll(keyword , pageRequest));

        // when
        final ResultActions actions = mvc.perform(get("/posts/search/{keyword}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
