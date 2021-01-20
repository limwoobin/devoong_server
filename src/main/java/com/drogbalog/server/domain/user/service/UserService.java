package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.domain.user.service.validator.impl.PasswordValidator;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.global.exception.Jwt.JwtCode;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResponse signUp(UserRequest request) {
        userValidator.signUpValidationCheck(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userDao.signUp(request);
    }

    @Transactional
    public UserResponse login(UserRequest request) {
        Validator validator = new PasswordValidator(userDao , passwordEncoder);
        validator.execute(request);

        UserResponse userResponse = userDao.findByEmail(request.getEmail());
        userResponse = jwtTokenProvider.generateTokens(userResponse);
        return userResponse;
    }

    public UserResponse getUserInfo(String email) {
        return userDao.findByEmail(email);
    }

    public UserResponse updateUserInfo(UserRequest request) {
        userValidator.userUpdateValidationCheck(request);
        return userDao.updateUserInfo(request);
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }

    public String getAccessToken(String email , String refreshToken) {
        if (!jwtTokenProvider.refreshTokenVerification(email , refreshToken)) {
            throw new UnAuthorizedException(JwtCode.EXPIRED.getCode() , "RefreshToken is Expired.");
        }

        return jwtTokenProvider.generateAccessToken(email);
    }
}
