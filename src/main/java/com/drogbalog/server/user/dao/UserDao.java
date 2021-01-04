package com.drogbalog.server.user.dao;

import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.user.converter.UserConverter;
import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserDao {
    private final UserRepository repository;
    private final UserConverter converter;

    public UserDto signUp(UserRequest request) {
        UserEntity userEntity = converter.userRequestConvert(request);
        UserDto userDto = converter.userConverts(repository.save(userEntity));
        return userDto;
    }

    public UserDto getUserInfo(long userId) {
        UserEntity userEntity = repository.findById(userId);
        return converter.userConverts(userEntity);
    }

    public UserDto updateUserInfo(UserRequest request) {
        UserEntity userEntity = repository.findById(request.getId());
        userEntity.update(request);
        repository.save(userEntity);
        return converter.userConverts(userEntity);
    }

    public void deleteUser(long userId) {
        try {
            repository.deleteById(userId);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST , "No Such UserId");
        }
    }

    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No Such Email"));
    }

    public UserEntity findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }
}
