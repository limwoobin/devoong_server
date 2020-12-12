package com.drogbalog.server.user.dao;

import com.drogbalog.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

}
