package com.drogbalog.server.user.service;

import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserDao userDao;

    public void test() {
        String a = null;
        if (a == null) {
            throw new DrogbalogException("ang~~");
        }
    }
}
