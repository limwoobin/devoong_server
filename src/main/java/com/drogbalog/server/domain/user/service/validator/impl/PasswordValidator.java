package com.drogbalog.server.domain.user.service.validator.impl;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordValidator implements Validator {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(UserRequest request) {
        String encodingPassword = userDao.getUserPassword(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword() , encodingPassword)) {
            throw new UnAuthorizedException("로그인 정보가 잘못되었습니다.");
        }
    }
}
