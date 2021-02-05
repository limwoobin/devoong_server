package com.drogbalog.server.domain.visit.service;

import com.drogbalog.server.domain.visit.domain.VisitResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class VisitService {
    private final RedisTemplate<String , Object> redisTemplate;
    private static final String REDIS_TODAY_KEY = "today";
    private static final String REDIS_FULL_DATE_KEY = "fullDate";

    public VisitResponse updateVisit() {
        VisitResponse visitResponse = new VisitResponse();
        try {
            visitResponse.setToday(getToday());
            visitResponse.setAllDay(getFullDate());
        } catch (Exception e) {
            log.error(e.getMessage());
            return defaultVisitResponse();
        }

        return visitResponse;
    }

    private Long getToday() {
        return redisTemplate.opsForValue().increment(REDIS_TODAY_KEY);
    }

    private Long getFullDate() {
        return redisTemplate.opsForValue().increment(REDIS_FULL_DATE_KEY);
    }

    private VisitResponse defaultVisitResponse() {
        VisitResponse visitResponse = new VisitResponse();
        visitResponse.reset();
        return visitResponse;
    }
}
