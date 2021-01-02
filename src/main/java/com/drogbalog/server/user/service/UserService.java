package com.drogbalog.server.user.service;

import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.validator.UserValidator;
import com.drogbalog.server.user.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserDto signUp(UserRequest request) {
        this.userValidationCheck(request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserDto userDto = userDao.signUp(request);
        return userDto;
    }

    private void userValidationCheck(UserRequest request) {
        boolean isUserRequest = userValidator.signUpValidationCheck(request);

        if (!isUserRequest) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST , "already in use data");
        }
    }

    public UserDto getUserInfo(long userId) {
        UserDto userDto = userDao.getUserInfo(userId);
        return userDto;
    }

    public UserDto updateUserInfo(UserRequest request) {
        UserDto userDto = userDao.updateUserInfo(request);
        return userDto;
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }
}
