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

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmailValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public void execute(UserRequest request) {
        if (nonAvailableEmailCheck(request.getEmail())) {
            throw new DuplicateDataException(DuplicateExceptionType.EMAIL_DUPLICATED);
        }
    }

    private boolean nonAvailableEmailCheck(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
//        return userRepository.findByEmail(email).isPresent();
    }
}
