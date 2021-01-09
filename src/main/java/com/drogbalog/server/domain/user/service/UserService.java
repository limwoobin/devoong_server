package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserResponse signUp(UserRequest request) {
        userValidator.signUpValidationCheck(request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserResponse userResponse = userDao.signUp(request);
        return userResponse;
    }

    public UserResponse login(UserRequest request) {
        UserResponse userResponse = userDao.login(request.getEmail() , passwordEncoder.encode(request.getPassword()));
        return userResponse;
    }

    public UserResponse getUserInfo(long userId) {
        UserResponse userResponse = userDao.getUserInfo(userId);
        return userResponse;
    }

    public UserResponse updateUserInfo(UserRequest request) {
        userValidator.userUpdateValidationCheck(request);

        UserResponse userResponse = userDao.updateUserInfo(request);
        return userResponse;
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }
}
