package com.drogbalog.server.domain.user.repository;

import com.drogbalog.server.global.config.jpa.DefaultRepository;
import com.drogbalog.server.domain.user.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends DefaultRepository<User, Long> {
    User findById(long id);

    Optional<User> findByEmail(String email);

    User findByNickName(String nickName);

    Optional<User> findByEmailAndPassword(String email , String password);
}

