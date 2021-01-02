package com.drogbalog.server.user.service;

import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserDto signUp(UserRequest request) {
        userValidator.signUpValidationCheck(request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserDto userDto = userDao.signUp(request);
        return userDto;
    }

    public UserDto getUserInfo(long userId) {
        UserDto userDto = userDao.getUserInfo(userId);
        return userDto;
    }

    public UserDto updateUserInfo(UserRequest request) {
        userValidator.userUpdateValidationCheck(request);

        UserDto userDto = userDao.updateUserInfo(request);
        return userDto;
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }
}
