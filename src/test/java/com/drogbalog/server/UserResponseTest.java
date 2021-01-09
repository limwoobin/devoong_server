package com.drogbalog.server;

import com.drogbalog.server.domain.user.domain.response.UserResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserResponseTest {

    @Test
    public void dto_test() {
        String email = "drogba02@naver.com";
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(email);

        assertThat(userResponse.getEmail()).isEqualTo(email);
    }
}
