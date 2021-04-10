package com.drogbalog.server.domain.tags.dao;

import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.mapper.TagMapper;
import com.drogbalog.server.domain.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagDao {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagsResponse> getTagsList() {
        List<TagsResponse> tagsResponseList = tagMapper.toTagResponseList(tagRepository.findAll());
        return tagsResponseList;
    }
}
