package com.drogbalog.server.user.service.validator;

import com.drogbalog.server.user.domain.request.UserRequest;

public interface Validator {
    boolean signUpValidator(UserRequest request);
}
