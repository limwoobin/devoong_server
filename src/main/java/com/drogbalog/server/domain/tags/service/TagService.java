package com.drogbalog.server.domain.tags.service;

import com.drogbalog.server.domain.tags.dao.TagDao;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagService {
    private final TagDao tagDao;

    @Transactional
    public List<TagsResponse> getTagsList() {
        return tagDao.getTagsList();
    }
}
