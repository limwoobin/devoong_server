package com.drogbalog.server.domain.tags.dao;

import com.drogbalog.server.domain.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class TagDao {
    private final TagRepository tagRepository;
}
