package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.response.JwtResponse;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.mapper.UserMapper;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.domain.user.service.validator.impl.PasswordValidator;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.global.exception.UserNotFoundException;
import com.drogbalog.server.global.exception.auth.AuthExceptionCode;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.drogbalog.server.global.exception.messages.CommonExceptionType.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String , Object> redisTemplate;
    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Transactional
    public UserResponse signUp(UserRequest request) {
        userValidator.signUpValidationCheck(request);
        request.encryptPassword(passwordEncoder);

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .imageUri(request.getImageUri())
                .role(request.getRole())
                .build();

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse login(UserRequest request) {
        Validator validator = new PasswordValidator(userRepository , passwordEncoder);
        validator.execute(request);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_USER));
        UserResponse userResponse = userMapper.toUserResponse(user);

        JwtResponse jwtResponse = jwtTokenProvider.generateTokens(request.getEmail());
        userResponse.setJwtResponse(jwtResponse);
        return userResponse;
    }

    public void logout(String email , String accessToken) {
        redisTemplate.delete(email);
        this.addBlackList(accessToken);
    }

    private void addBlackList(String accessToken) {
        Date expirationDate = jwtTokenProvider.getExpirationDate(accessToken);
        redisTemplate.opsForValue().set(accessToken , true);
        redisTemplate.expire(accessToken , expirationDate.getTime() - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    public UserResponse getUserInfo(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_USER));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUserInfo(UserRequest request) {
        userValidator.userUpdateValidationCheck(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_USER));

        user.updateUserInfo(request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(long userId) {
        User user = userRepository.findById(userId);
        user.updateStatus(Status.DISABLE);
    }

    public String getAccessToken(String email , String refreshToken) {
        if (!jwtTokenProvider.refreshTokenVerification(email , refreshToken)) {
            throw new UnAuthorizedException(REFRESH_TOKEN_IS_EXPIRED , AuthExceptionCode.EXPIRED);
        }

        return jwtTokenProvider.generateAccessToken(email);
    }
}
