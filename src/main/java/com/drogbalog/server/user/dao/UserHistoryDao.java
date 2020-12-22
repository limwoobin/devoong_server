package com.drogbalog.server.user.dao;

import com.drogbalog.server.user.converter.UserConverter;
import com.drogbalog.server.user.domain.dto.UserHistoryDto;
import com.drogbalog.server.user.domain.entity.UserHistoryEntity;
import com.drogbalog.server.user.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserHistoryDao {
    private final UserHistoryRepository repository;
    private final UserConverter userConverter;

    public UserHistoryDto getUserLoginHistory(long userId) {
        UserHistoryEntity userHistoryEntity = repository.findByUserId(userId);
        UserHistoryDto userHistoryDto = userConverter.userHistoryConverts(userHistoryEntity);

        return userHistoryDto;
    }
}
