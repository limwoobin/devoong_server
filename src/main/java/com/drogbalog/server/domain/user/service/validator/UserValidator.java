package com.drogbalog.server.domain.user.service.validator;

import com.drogbalog.server.domain.user.service.validator.impl.NickNameValidator;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.validator.impl.EmailValidator;
import com.drogbalog.server.domain.user.service.validator.impl.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final EmailValidator emailValidator;
    private final NickNameValidator nickNameValidator;

    public void signUpValidationCheck(UserRequest request) {
        List<Validator> signUpValidators = List.of(emailValidator , nickNameValidator);
        this.validatorLoop(signUpValidators, request);
    }

    public void userUpdateValidationCheck(UserRequest request) {
        List<Validator> userUpdateValidators = List.of(nickNameValidator);
        this.validatorLoop(userUpdateValidators, request);
    }

    private void validatorLoop(List<Validator> validators , UserRequest request) {
        for (Validator validator : validators) {
            validator.execute(request);
        }
    }
}
