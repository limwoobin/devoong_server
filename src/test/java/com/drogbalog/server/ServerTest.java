package com.drogbalog.server;

import com.drogbalog.server.domain.user.domain.entity.UserEntity;
import com.drogbalog.server.domain.user.repository.UserRepository;
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
//        UserEntity userEntity = UserEntity.builder()
//                .email("drogba02@naver.com")
//                .password("zzz")
//                .nickName("drogba")
//                .build();
//        repository.save(userEntity);
//        UserEntity user = repository.findById(1L);
//        assertEquals("drogba" , user.getNickName());
        UserEntity entity = repository.findByEmail("azaz")
                .orElseThrow(RuntimeException::new);

        System.out.println(entity.getEmail());
    }
}
