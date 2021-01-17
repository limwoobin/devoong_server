package com.drogbalog.server;

import com.drogbalog.server.domain.admin.api.AdminApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminApi.class)
public class RestTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserTest() throws Exception {
        mvc.perform(get("/admin/test"))
                .andExpect(status().isOk());
    }
}
