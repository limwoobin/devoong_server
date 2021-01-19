package com.drogbalog.server.domain.user.service.validator.impl;

import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.global.exception.DuplicateDataException;
import com.drogbalog.server.global.exception.DuplicateStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class EmailValidator implements Validator {
    private final UserDao userDao;

    @Override
    public void execute(UserRequest request) {
        User user = userDao.availableEmailCheck(request.getEmail());
        if (!StringUtils.isEmpty(user)) {
            throw new DuplicateDataException(
                    DuplicateStatus.EMAIL_DUPLICATED.getCode(),
                    DuplicateStatus.EMAIL_DUPLICATED.getMessage());
        }
    }
}
