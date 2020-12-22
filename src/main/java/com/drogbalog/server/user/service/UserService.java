package com.drogbalog.server.user.service;

import com.drogbalog.server.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserDao userDao;

}
