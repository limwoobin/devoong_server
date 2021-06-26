package com.drogbalog.server.domain.user.service.validator.impl;

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
public class EmailValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public void execute(UserRequest request) {
        if (nonAvailableEmailCheck(request.getEmail())) {
            throw new DuplicateDataException(
                    DuplicateExceptionType.EMAIL_DUPLICATED.getCode(),
                    DuplicateExceptionType.EMAIL_DUPLICATED.getMessage());
        }
    }

    private boolean nonAvailableEmailCheck(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
