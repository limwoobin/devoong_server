package com.drogbalog.server.domain.user.dao;

import com.drogbalog.server.domain.user.converter.UserConverter;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.domain.user.domain.dto.UserDto;
import com.drogbalog.server.domain.user.domain.entity.UserEntity;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository repository;
    private final UserConverter converter;

    @Transactional
    public UserDto signUp(UserRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickName(request.getNickName())
                .profileImagePath(request.getProfileImagePath())
                .gender(request.getGender())
                .status(request.getStatus())
                .role(request.getRole())
                .build();

        return converter.userConverts(repository.save(userEntity));
    }

    @Transactional
    public UserDto login(String email , String password) {
        UserEntity userEntity = repository.findByEmailAndPassword(email , password)
                .orElseThrow(() -> new BadRequestException("로그인 정보를 다시 확인해주세요."));

        return converter.userConverts(userEntity);
    }

    @Transactional
    public UserDto getUserInfo(long userId) {
        UserEntity userEntity = repository.findById(userId);
        return converter.userConverts(userEntity);
    }

    @Transactional
    public UserDto updateUserInfo(UserRequest request) {
        UserEntity userEntity = repository.findById(request.getId());
        userEntity.update(request);
        repository.save(userEntity);
        return converter.userConverts(userEntity);
    }

    @Transactional
    public void deleteUser(long userId) {
        UserEntity userEntity = repository.findById(userId);
        userEntity.updateStatus(Status.DISABLE);
        repository.save(userEntity);
    }

    @Transactional
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No Such Email"));
    }

    @Transactional
    public UserEntity availableEmailCheck(String email) {
        return repository.findByEmail(email)
                .orElse(null);
    }

    @Transactional
    public UserEntity findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }
}
