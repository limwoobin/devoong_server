package com.drogbalog.server.user.service.validator;

import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.service.validator.impl.EmailValidator;
import com.drogbalog.server.user.service.validator.impl.NickNameValidator;
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
        return this.validatorLoop(validators , request);
    }

    public boolean userUpdateValidationCheck(UserRequest request) {
        List<Validator> validators = Arrays.asList(new NickNameValidator(userDao));
        return this.validatorLoop(validators , request);
    }

    private boolean validatorLoop(List<Validator> validators , UserRequest request) {
        boolean isRequest = false;
        for (Validator validator : validators) {
            isRequest = validator.signUpValidator(request);
        }

        return isRequest;
    }
}
