package com.drogbalog.server;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ServerTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void test() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("drogba02@naver.com");
        userEntity.setPassword("zzzz");
        userEntity.setGender(Gender.MALE);
        userEntity.setNickName("drogba");
        repository.save(userEntity);

        UserEntity user = repository.findById(1L).orElseThrow(() -> new RuntimeException());
        assertEquals("drogba" , user.getNickName());
    }
}
