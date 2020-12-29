package com.drogbalog.server.user.dao;

import com.drogbalog.server.user.converter.UserConverter;
import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository repository;
    private final UserConverter converter;

    public UserDto getUserInfo(long userId) {
        UserEntity userEntity = repository.findById(userId);
        return converter.userConverts(userEntity);
    }
}
