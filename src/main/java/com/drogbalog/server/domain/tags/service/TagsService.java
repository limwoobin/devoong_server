package com.drogbalog.server.domain.tags.service;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.mapper.TagsMapper;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import com.drogbalog.server.global.code.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagsService {
    private final TagsRepository tagsRepository;
    private final TagsMapper tagsMapper = TagsMapper.INSTANCE;

    @Transactional(readOnly = true)
    public List<TagsResponse> getTagsList() {
        List<Tags> tagsList = tagsRepository.findAllByStatus(Status.ACTIVE);
        if (tagsList.size() > 0) {
            return tagsMapper.toTagResponseList(tagsList);
        }

        return Collections.emptyList();
    }
}
