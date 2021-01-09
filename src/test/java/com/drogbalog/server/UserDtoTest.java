package com.drogbalog.server;

import com.drogbalog.server.domain.user.domain.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDtoTest {

    @Test
    public void dto_test() {
        String email = "drogba02@naver.com";
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        assertThat(userDto.getEmail()).isEqualTo(email);
    }
}
