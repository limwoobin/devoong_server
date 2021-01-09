package com.drogbalog.server.domain.user.service.validator;

import com.drogbalog.server.domain.user.domain.request.UserRequest;

public interface Validator {
    boolean signUpValidator(UserRequest request);
}
