package com.drogbalog.server;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.config.security.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserJpaTest {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        User user = User.builder()
                .email("drogbacuty@gmail.com")
                .password("wjqhsaoal22")
                .nickname("cutydrogba")
                .profileImagePath("deault")
                .gender(Gender.MALE)
                .role(Role.USER)
                .build();

        System.out.println(user.getId());

        User updateUser = userRepository.save(user);

        System.out.println(user.getEmail() + "," + user.getId());
        System.out.println(updateUser.getEmail() + "," + updateUser.getId());
    }
}
