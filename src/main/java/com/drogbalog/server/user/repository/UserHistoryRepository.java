package com.drogbalog.server.user.repository;

import com.drogbalog.server.global.config.jpa.DefaultRepository;
import com.drogbalog.server.user.domain.entity.UserHistoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends DefaultRepository<UserHistoryEntity , Long> {
}
