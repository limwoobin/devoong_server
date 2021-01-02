package com.drogbalog.server.user.validator;

import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.validator.impl.EmailValidator;
import com.drogbalog.server.user.validator.impl.NickNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final UserDao userDao;

    public boolean signUpValidationCheck(UserRequest request) {
        List<Validator> validators = Arrays.asList(new EmailValidator(userDao) , new NickNameValidator(userDao));
        boolean isUserRequest = false;

        for (Validator validator : validators) {
            isUserRequest = validator.signUpValidator(request);
        }

        return isUserRequest;
    }
}
