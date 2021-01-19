package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.domain.user.service.validator.impl.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public UserResponse signUp(UserRequest request) {
        userValidator.signUpValidationCheck(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userDao.signUp(request);
    }

    @Transactional
    public UserResponse login(UserRequest request) {
        Validator validator = new PasswordValidator(userDao , passwordEncoder);
        validator.execute(request);

        return userDao.findByEmail(request.getEmail());
    }

    public UserResponse getUserInfo(long userId) {
        return userDao.getUserInfo(userId);
    }

    public UserResponse updateUserInfo(UserRequest request) {
        userValidator.userUpdateValidationCheck(request);
        return userDao.updateUserInfo(request);
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }
}
