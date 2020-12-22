package com.drogbalog.server.user.service;

import com.drogbalog.server.user.dao.UserHistoryDao;
import com.drogbalog.server.user.domain.dto.UserHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHistoryService {
    private final UserHistoryDao userHistoryDao;

    public UserHistoryDto getUserLoginHistory(long userId) {
        UserHistoryDto userHistoryDto = userHistoryDao.getUserLoginHistory(userId);
        return userHistoryDto;
    }
}
