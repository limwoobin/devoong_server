package com.drogbalog.server.domain.tags.service;

import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.mapper.TagsMapper;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import com.drogbalog.server.global.code.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagsService {
    private final TagsRepository tagsRepository;
    private final TagsMapper tagsMapper;

    @Transactional
    public List<TagsResponse> getTagsList() {
        return tagsMapper.toTagResponseList(tagsRepository.findAllByStatus(Status.ACTIVE));
    }
}
