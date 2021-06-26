package com.drogbalog.server.domain.tags.service;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.repository.TagsRepository;
import com.drogbalog.server.global.code.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.drogbalog.server.domain.tags.service.TagsTestDomain.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("태그 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class TagsServiceTest {

    @Mock
    private TagsRepository tagsRepository;

    @InjectMocks
    private TagsService tagsService;

    @Test
    @DisplayName("사용가능한 태그 목록 조회 테스트 - 빈 리스트 반환")
    public void empty_getTagsListTest() {
        // given
        Status status = Status.ACTIVE;

        // when
        when(tagsRepository.findAllByStatus(status)).thenReturn(빈_태그_목록);

        // then
        List<TagsResponse> tagsResponseList = tagsService.getTagsList();
        assertThat(tagsResponseList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("사용가능한 태그 목록 조회 테스트 - 리스트 반환")
    public void getTagsListTest() {
        // given
        Status status = Status.ACTIVE;

        // when
        when(tagsRepository.findAllByStatus(status)).thenReturn(태그_목록);

        // then
        List<TagsResponse> tagsResponseList = tagsService.getTagsList();
        assertThat(tagsResponseList.size()).isEqualTo(태그_목록.size());
    }
}

final class TagsTestDomain {
    static final Tags tags = Tags.builder()
            .id(1L)
            .name("tags1")
            .build();

    static final Tags tags2 = Tags.builder()
            .id(2L)
            .name("tags2")
            .build();

    static final Tags tags3 = Tags.builder()
            .id(3L)
            .name("tags3")
            .build();

    static final Tags tags4 = Tags.builder()
            .id(4L)
            .name("tags4")
            .build();

    static final List<Tags> 빈_태그_목록 = new ArrayList<>();
    static final List<Tags> 태그_목록 = List.of(tags , tags2 , tags3 , tags4);
}