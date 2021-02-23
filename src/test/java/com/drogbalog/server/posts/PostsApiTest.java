package com.drogbalog.server.posts;

import com.drogbalog.server.domain.posts.api.PostsApi;
import com.drogbalog.server.domain.posts.service.PostsService;
import com.drogbalog.server.global.config.security.SecurityConfiguration;
import com.drogbalog.server.global.config.security.jwt.CustomUserDetailService;
import com.drogbalog.server.global.config.security.jwt.JwtAuthenticationInterceptor;
import com.drogbalog.server.global.config.security.oauth.CustomOAuth2UserService;
import com.drogbalog.server.global.config.security.oauth.OAuth2AuthenticationFailureHandler;
import com.drogbalog.server.global.config.security.oauth.OAuth2AuthenticationSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Log4j2
@WebMvcTest(PostsApi.class)
@Import(SecurityConfiguration.class)
public class PostsApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsService postsService;

    @Test
    public void getSearchAll_test() throws Exception {
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
