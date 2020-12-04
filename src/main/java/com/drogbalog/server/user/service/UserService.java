package com.drogbalog.server.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("user")
@RequiredArgsConstructor
public class UserService implements com.drogbalog.server.user.service.Service {

    @Override
    public String serviceTest() {
        return "userService";
    }
}
