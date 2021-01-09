package com.drogbalog.server.domain.user.dao;

import com.drogbalog.server.domain.user.converter.UserConverter;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.entity.User;
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
    public UserResponse signUp(UserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickName(request.getNickName())
                .profileImagePath(request.getProfileImagePath())
                .gender(request.getGender())
                .status(request.getStatus())
                .role(request.getRole())
                .build();

        return converter.userConverts(repository.save(user));
    }

    @Transactional
    public UserResponse login(String email , String password) {
        User user = repository.findByEmailAndPassword(email , password)
                .orElseThrow(() -> new BadRequestException("로그인 정보를 다시 확인해주세요."));

        return converter.userConverts(user);
    }

    @Transactional
    public UserResponse getUserInfo(long userId) {
        User user = repository.findById(userId);
        return converter.userConverts(user);
    }

    @Transactional
    public UserResponse updateUserInfo(UserRequest request) {
        User user = repository.findById(request.getId());
        user.update(request);
        repository.save(user);
        return converter.userConverts(user);
    }

    @Transactional
    public void deleteUser(long userId) {
        User user = repository.findById(userId);
        user.updateStatus(Status.DISABLE);
        repository.save(user);
    }

    @Transactional
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No Such Email"));
    }

    @Transactional
    public User availableEmailCheck(String email) {
        return repository.findByEmail(email)
                .orElse(null);
    }

    @Transactional
    public User findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }
}
