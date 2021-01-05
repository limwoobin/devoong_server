package com.drogbalog.server.user.repository;

import com.drogbalog.server.global.config.jpa.DefaultRepository;
import com.drogbalog.server.user.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends DefaultRepository<UserEntity , Long> {
    UserEntity findById(long id);

    Optional<UserEntity> findByEmail(String email);

    UserEntity findByNickName(String nickName);
}

