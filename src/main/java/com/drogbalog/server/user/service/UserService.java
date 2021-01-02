package com.drogbalog.server.user.service;

import com.drogbalog.server.user.dao.UserDao;
import com.drogbalog.server.user.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final UserDao userDao;

    public UserDto getUserInfo(long userId) {
        UserDto userDto = userDao.getUserInfo(userId);
        return userDto;
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }
}
