package com.drogbalog.server.domain.tags.dao;

import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.mapper.TagsMapper;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagsDao {
    private final TagsRepository tagsRepository;
    private final TagsMapper tagsMapper;

    public List<TagsResponse> getTagsList() {
        List<TagsResponse> tagsResponseList = tagsMapper.toTagResponseList(tagsRepository.findAll());
        return tagsResponseList;
    }
}
