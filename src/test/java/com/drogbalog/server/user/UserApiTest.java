package com.drogbalog.server.user;

import com.drogbalog.server.domain.user.api.UserApi;
import com.drogbalog.server.domain.user.service.UserService;
import com.drogbalog.server.global.config.security.SecurityConfiguration;
import com.drogbalog.server.global.config.security.jwt.JwtAuthenticationInterceptor;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log4j2
@WebMvcTest(
        controllers = UserApi.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = SecurityConfiguration.class
                )
        }
)
public class UserApiTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtAuthenticationInterceptor interceptor;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void get_test() throws Exception {
        log.info("GetUser Test");

        final ResultActions actions = mvc.perform(get("/users/1")
                .header("X-AUTH-TOKEN" , "jwtTokenTest")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void getUser_serviceTest() throws Exception {
        log.info("GetUser Service Test");

        // given
        final long userId = 1;
        final String email = "drogba02@naver.com";

        given(userService.getUserInfo(email))
                .willReturn(null);

        // when
        final ResultActions actions = mvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        actions
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void logout_test() throws Exception {
        log.info("Logout Test");

        final ResultActions actions = mvc.perform(get("/users/logout")
                .header("X-AUTH-TOKEN" , "jwtTokenTest"));

        actions
                .andExpect(status().isOk())
                .andDo(print());
    }
}
