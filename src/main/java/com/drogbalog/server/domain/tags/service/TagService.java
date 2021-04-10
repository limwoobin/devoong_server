package com.drogbalog.server.domain.tags.service;

import com.drogbalog.server.domain.tags.dao.TagDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagService {
    private final TagDao tagDao;
}
