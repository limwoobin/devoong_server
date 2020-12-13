package com.drogbalog.server.user.dao;

import com.drogbalog.server.user.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHistoryDao {
    private final UserHistoryRepository repository;

}
