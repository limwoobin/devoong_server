package com.drogbalog.server.user.service;

import com.drogbalog.server.user.dao.UserHistoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHistoryService {
    private final UserHistoryDao userHistoryDao;
}
