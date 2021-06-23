package com.drogbalog.server.domain.user.service.validator.impl;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.Validator;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import com.drogbalog.server.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.drogbalog.server.global.exception.messages.CommonExceptionType.*;

@RequiredArgsConstructor
public class PasswordValidator implements Validator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(UserRequest request) {
        String encodingPassword = this.getUserPassword(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword() , encodingPassword)) {
            throw new UnAuthorizedException(INVALID_LOGIN_INFO);
        }
    }

    private String getUserPassword(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_USER));
        return user.getPassword();
    }
}
