package com.drogbalog.server.user.validator;

import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.validator.impl.EmailValidator;
import com.drogbalog.server.user.validator.impl.NickNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final UserDao userDao;

    public boolean signUpValidationCheck(UserRequest request) {
        Validator emailValidator = new EmailValidator(userDao);
        boolean isEmail = emailValidator.signUpValidator(request);

        Validator nickNameValidator = new NickNameValidator(userDao);
        boolean isNickName = nickNameValidator.signUpValidator(request);

        return isEmail && isNickName;
    }
}
