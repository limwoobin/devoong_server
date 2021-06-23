package com.drogbalog.server.domain.user.service.validator.impl;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.global.exception.DuplicateDataException;
import com.drogbalog.server.global.exception.messages.DuplicateExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class NickNameValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public void execute(UserRequest request) {
        User user = userRepository.findByNickname(request.getNickname());
        if (!StringUtils.isEmpty(user)) {
            throw new DuplicateDataException(
                    DuplicateExceptionType.NICKNAME_DUPLICATED.getCode(),
                    DuplicateExceptionType.NICKNAME_DUPLICATED.getMessage());
        }
    }
}
