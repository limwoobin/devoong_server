package com.drogbalog.server.domain.user.service.validator;

import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.impl.NickNameValidator;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.impl.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final UserRepository userRepository;

    public void signUpValidationCheck(UserRequest request) {
        List<Validator> validators = List.of(new EmailValidator(userRepository) , new NickNameValidator(userRepository));
        this.validatorLoop(validators, request);
    }

    public void userUpdateValidationCheck(UserRequest request) {
        List<Validator> validators = Collections.singletonList(new NickNameValidator(userRepository));
        this.validatorLoop(validators, request);
    }

    private void validatorLoop(List<Validator> validators , UserRequest request) {
        for (Validator validator : validators) {
            validator.execute(request);
        }
    }
}
