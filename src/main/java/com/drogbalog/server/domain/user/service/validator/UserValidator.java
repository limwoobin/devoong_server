package com.drogbalog.server.domain.user.service.validator;

import com.drogbalog.server.domain.user.service.validator.impl.NickNameValidator;
import com.drogbalog.server.domain.user.dao.UserDao;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.impl.EmailValidator;
import com.drogbalog.server.domain.user.service.validator.impl.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final UserDao userDao;

    public void signUpValidationCheck(UserRequest request) {
        List<Validator> validators = Arrays.asList(new EmailValidator(userDao) , new NickNameValidator(userDao));
        this.validatorLoop(validators, request);
    }

    public void userUpdateValidationCheck(UserRequest request) {
        List<Validator> validators = Collections.singletonList(new NickNameValidator(userDao));
        this.validatorLoop(validators, request);
    }

    private void validatorLoop(List<Validator> validators , UserRequest request) {
        for (Validator validator : validators) {
            validator.execute(request);
        }
    }
}
