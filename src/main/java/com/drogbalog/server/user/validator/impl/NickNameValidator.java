package com.drogbalog.server.user.validator.impl;

import com.drogbalog.server.global.exception.BadRequestException;
import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class NickNameValidator implements Validator {
    private final UserDao userDao;

    @Override
    public boolean signUpValidator(UserRequest request) {
        UserEntity userEntity = userDao.findByNickName(request.getNickName());
        if (!StringUtils.isEmpty(userEntity)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST , "Already in use nickName");
        }

        return true;
    }
}
