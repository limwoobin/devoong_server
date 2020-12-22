package com.drogbalog.server.user.dao;

import com.drogbalog.server.user.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserHistoryDao {
    private final UserHistoryRepository repository;

}
