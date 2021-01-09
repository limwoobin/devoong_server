package com.drogbalog.server.domain.user.service.validator.impl;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.domain.user.service.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class EmailValidator implements Validator {
    private final UserDao userDao;

    @Override
    public boolean signUpValidator(UserRequest request) {
        User user = userDao.availableEmailCheck(request.getEmail());
        if (!StringUtils.isEmpty(user)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST , "이미 사용중인 이메일 입니다.");
        }

        return true;
    }
}