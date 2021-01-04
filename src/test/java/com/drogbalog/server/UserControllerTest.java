package com.drogbalog.server;

import com.drogbalog.server.user.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Test
//    public void loginTest() throws Exception {
//        mvc.perform(get("/user/login"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getUserTest() throws Exception {
//        String nickName = "drogba";
//        mvc.perform(get("/user/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(nickName));
//    }
}
