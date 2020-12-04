package com.drogbalog.server.user.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("test")
public class TestService implements com.drogbalog.server.user.service.Service {
    @Override
    public String serviceTest() {
        return "testService";
    }
}
