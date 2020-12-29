package com.drogbalog.server.user.repository;

import com.drogbalog.server.global.config.jpa.DefaultRepository;
import com.drogbalog.server.user.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends DefaultRepository<UserEntity , Long> {
    UserEntity findById(long id);
}

