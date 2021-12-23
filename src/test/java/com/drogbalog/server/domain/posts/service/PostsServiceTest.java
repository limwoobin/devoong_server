package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.dto.Archive;
import com.drogbalog.server.domain.posts.domain.dto.ArchiveByYear;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.dto.PostsCardList;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import com.drogbalog.server.infra.retrofit.GithubApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.drogbalog.server.domain.posts.service.PostsTestDomain.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@DisplayName("게시글 조회 테스트")
@ExtendWith(MockitoExtension.class)
class PostsServiceTest {

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private PostsTagsMappingRepository postsTagsMappingRepository;

    @Mock
    private GithubApiClient githubApiClient;

    @InjectMocks
    private PostsService postsService;

    private final PostsMapper postsMapper = PostsMapper.INSTANCE;

    @Nested
    @DisplayName("게시글 목록 조회 테스트")
    class GetPostsAllTest {

        @Test
        @DisplayName("리스트가 빈값이면 빈값으로 반환되어야 한다")
        public void empty_getPostsListTest() {
            // given
            PageRequest pageRequest = PostsTestDomain.pageRequest;

            // when
            when(postsRepository.findAllPostsAndTags(pageRequest))
                    .thenReturn(Collections.emptyList());

            // then
            Page<PostsResponse> result = postsService.getPostsList(pageRequest);
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("리스트에 값이 있으면 정상적으로 반환되어야 한다")
        public void getPostsListTest() {
            // given
            List<Posts> 게시글_목록 = new ArrayList<>(게시글_리스트);

            // when
            when(postsRepository.findAllPostsAndTags(pageRequest))
                    .thenReturn(게시글_목록);

            // then
            Page<PostsResponse> result = postsService.getPostsList(pageRequest);
            assertThat(result.getContent().size()).isEqualTo(4);
            assertThat(result.getContent().get(0).getId()).isEqualTo(1L);
            assertThat(result.getContent().get(1).getId()).isEqualTo(2L);
            assertThat(result.getContent().get(2).getId()).isEqualTo(3L);
            assertThat(result.getContent().get(3).getId()).isEqualTo(4L);
        }

        @Test
        @DisplayName("태그번호로 게시글 리스트 조회시 빈값은 빈값으로 반환되어야 한다")
        public void empty_getPostsListByTagsIdTest() {
            // given
            PageRequest pageRequest = PostsTestDomain.pageRequest;
            String name = "testCode";

            // when
            when(postsTagsMappingRepository.findAllByTagsName(pageRequest , name))
                    .thenReturn(Page.empty());

            // then
            Page<PostsResponse> result = postsService.getPostsListByTagsName(pageRequest , name);
            assertThat(result).isEqualTo(Page.empty());
        }

        @Test
        @DisplayName("태그번호로 게시글 리스트 조회시 정상적으로 반환되어야 한다")
        public void getPostsListByTagsIdTest() {
            // given
            PageRequest pageRequest = PostsTestDomain.pageRequest;
            String name = "testCode";
            List<PostsResponse> postsResponseList = List.of(
                    postsMapper.converts(posts),
                    postsMapper.converts(posts2),
                    postsMapper.converts(posts3)
            );

            // when
            when(postsTagsMappingRepository.findAllByTagsName(pageRequest , name))
                    .thenReturn(new PageImpl<>(postsResponseList , pageRequest , postsResponseList.size()));

            // then
            Page<PostsResponse> result = postsService.getPostsListByTagsName(pageRequest , name);
            assertThat(result.getTotalElements()).isEqualTo(3);
            assertThat(result.getContent()).isEqualTo(postsResponseList);
        }
    }

    @Nested
    @DisplayName("최근 게시글 조회 테스트")
    class LatestPostsTest {
        @Test
        @DisplayName("최근 게시물 조회시 최근 3개 혹은 3개 이하의 데이터가 나와야 한다")
        public void getLatestPostsTest() {
            // case 1
            // when
            when(postsRepository.findTop3ByStatusOrderByIdDesc(Status.ACTIVE)).thenReturn(Collections.emptyList());

            // then
            List<PostsResponse> result = postsService.getLatestPosts();
            assertThat(result.size()).isEqualTo(0);

            // case 2
            // when
            when(postsRepository.findTop3ByStatusOrderByIdDesc(Status.ACTIVE)).thenReturn(List.of(posts , posts2 , posts3));

            // then
            List<PostsResponse> result2 = postsService.getLatestPosts();
            assertThat(result2.size()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("단일 컨텐츠 조회 테스트")
    class GetPostsTest {
        @Test
        @DisplayName("게시글 조회시 데이터가 없다면 예외가 발생해야 한다")
        public void emptyData_exception() {
            // given
            long id = 1L;

            // when
            when(postsRepository.findById(id)).thenReturn(Optional.empty());

            // then
            assertThatThrownBy(() -> {
                postsService.getPosts(id);
            }).isInstanceOf(EmptyDataException.class)
                    .hasMessageContaining(EmptyDataExceptionType.EMPTY_POSTS_DATA.getMessage());
        }

        @Test
        @DisplayName("게시글 조회시 데이터가 있다면 정상적으로 조회되어야 한다")
        public void findTest() {
            // given
            long id = posts.getId();

            // when
            when(postsRepository.findById(id)).thenReturn(Optional.of(posts));
            when(postsTagsMappingRepository.findTagsByPostsId(id)).thenReturn(Collections.emptyList());
            when(postsRepository.findPreviousAndNextPostsCardById(id)).thenReturn(Collections.emptyList());
            when(githubApiClient.callMarkdownApi(any())).thenReturn("test");

            // then
            PostsResponse postsResponse = postsService.getPosts(id);
            assertThat(postsResponse.getId()).isEqualTo(id);
            assertThat(postsResponse.getContents()).isEqualTo("test");
        }
    }
    
    @Nested
    @DisplayName("이전글 , 다음글 게시글 조회")
    class PreviousAndNextPosts {

        @Test
        @DisplayName("게시글이 총 1개인 경우엔 이전 게시글 , 다음 게시글은 null 이어야 한다")
        void getPostsCardList() {
            // given
            long id = firstPostsCard.getId();
            List<PostsCard> postsCards = List.of(firstPostsCard);

            // when
            when(postsRepository.findPreviousAndNextPostsCardById(id))
                    .thenReturn(postsCards);

            // then
            PostsCardList postsCardList = postsService.getPostsCardList(id);
            assertThat(postsCardList.getPreviousPostsCard()).isNull();
            assertThat(postsCardList.getNextPostsCard()).isNull();
            assertThat(postsCardList.getPostsCardList().get(0).getId()).isEqualTo(id);
        }

        @Test
        @DisplayName("게시글이 총 2개인경우 첫번째 게시글을 조회했을시 이전 게시글은 null 이어야 한다. " +
                "현재 게시글 , 다음 게시글은 값이 존재해야 한다")
        void getPostsCardList2() {
            // given
            long id = firstPostsCard.getId();
            List<PostsCard> postsCards = List.of(firstPostsCard , secondPostsCard);

            // when
            when(postsRepository.findPreviousAndNextPostsCardById(id))
                    .thenReturn(postsCards);

            // then
            PostsCardList postsCardList = postsService.getPostsCardList(id);
            assertThat(postsCardList.getPreviousPostsCard()).isNull();

            assertThat(postsCardList.getPostsCardList().get(0).getId()).isEqualTo(firstPostsCard.getId());
            assertThat(postsCardList.getPostsCardList().get(0).getTitle()).isEqualTo(firstPostsCard.getTitle());
            assertThat(postsCardList.getPostsCardList().get(0).getBannerImage()).isEqualTo(firstPostsCard.getBannerImage());

            assertThat(postsCardList.getNextPostsCard().getId()).isEqualTo(secondPostsCard.getId());
            assertThat(postsCardList.getNextPostsCard().getTitle()).isEqualTo(secondPostsCard.getTitle());
            assertThat(postsCardList.getNextPostsCard().getBannerImage()).isEqualTo(secondPostsCard.getBannerImage());
        }

        @Test
        @DisplayName("마지막 게시글을 조회했을시 다음 게시글은 null 이어야 한다, " +
                "이전 게시글 , 현재 게시글은 값이 존재해야 한다")
        void getPostsCardList3() {
            // given
            long id = secondPostsCard.getId();
            List<PostsCard> postsCards = List.of(firstPostsCard , secondPostsCard);

            // when
            when(postsRepository.findPreviousAndNextPostsCardById(id))
                    .thenReturn(postsCards);

            // then
            PostsCardList postsCardList = postsService.getPostsCardList(id);

            assertThat(postsCardList.getPreviousPostsCard().getId()).isEqualTo(firstPostsCard.getId());
            assertThat(postsCardList.getPreviousPostsCard().getTitle()).isEqualTo(firstPostsCard.getTitle());
            assertThat(postsCardList.getPreviousPostsCard().getBannerImage()).isEqualTo(firstPostsCard.getBannerImage());

            assertThat(postsCardList.getPostsCardList().get(1).getId()).isEqualTo(secondPostsCard.getId());
            assertThat(postsCardList.getPostsCardList().get(1).getTitle()).isEqualTo(secondPostsCard.getTitle());
            assertThat(postsCardList.getPostsCardList().get(1).getBannerImage()).isEqualTo(secondPostsCard.getBannerImage());

            assertThat(postsCardList.getNextPostsCard()).isNull();
        }

        @Test
        @DisplayName("마지막 게시글을 조회했을시 다음 게시글은 null 이어야 한다, " +
                "이전 게시글 , 현재 게시글은 값이 존재해야 한다")
        void getPostsCardList4() {
            // given
            long id = thirdPostsCard.getId();
            List<PostsCard> postsCards = List.of(secondPostsCard , thirdPostsCard);

            // when
            when(postsRepository.findPreviousAndNextPostsCardById(id))
                    .thenReturn(postsCards);

            // then
            PostsCardList postsCardList = postsService.getPostsCardList(id);

            assertThat(postsCardList.getPreviousPostsCard().getId()).isEqualTo(secondPostsCard.getId());
            assertThat(postsCardList.getPreviousPostsCard().getTitle()).isEqualTo(secondPostsCard.getTitle());
            assertThat(postsCardList.getPreviousPostsCard().getBannerImage()).isEqualTo(secondPostsCard.getBannerImage());

            assertThat(postsCardList.getPostsCardList().get(1).getId()).isEqualTo(thirdPostsCard.getId());
            assertThat(postsCardList.getPostsCardList().get(1).getTitle()).isEqualTo(thirdPostsCard.getTitle());
            assertThat(postsCardList.getPostsCardList().get(1).getBannerImage()).isEqualTo(thirdPostsCard.getBannerImage());

            assertThat(postsCardList.getNextPostsCard()).isNull();
        }

        @Test
        @DisplayName("게시글을 조회했을시 이전 게시글 , 현재 게시글이 존재한다면 값이 존재해야 한다")
        void getPostsCardList5() {
            // given
            long id = secondPostsCard.getId();
            List<PostsCard> postsCards = List.of(firstPostsCard , secondPostsCard , thirdPostsCard);

            // when
            when(postsRepository.findPreviousAndNextPostsCardById(id))
                    .thenReturn(postsCards);

            // then
            PostsCardList postsCardList = postsService.getPostsCardList(id);

            assertThat(postsCardList.getPreviousPostsCard().getId()).isEqualTo(firstPostsCard.getId());
            assertThat(postsCardList.getPreviousPostsCard().getTitle()).isEqualTo(firstPostsCard.getTitle());
            assertThat(postsCardList.getPreviousPostsCard().getBannerImage()).isEqualTo(firstPostsCard.getBannerImage());

            assertThat(postsCardList.getPostsCardList().get(1).getId()).isEqualTo(secondPostsCard.getId());
            assertThat(postsCardList.getPostsCardList().get(1).getTitle()).isEqualTo(secondPostsCard.getTitle());
            assertThat(postsCardList.getPostsCardList().get(1).getBannerImage()).isEqualTo(secondPostsCard.getBannerImage());

            assertThat(postsCardList.getNextPostsCard().getId()).isEqualTo(thirdPostsCard.getId());
            assertThat(postsCardList.getNextPostsCard().getTitle()).isEqualTo(thirdPostsCard.getTitle());
            assertThat(postsCardList.getNextPostsCard().getBannerImage()).isEqualTo(thirdPostsCard.getBannerImage());
        }
    }

    @Nested
    @DisplayName("Archive 테스트")
    class ArchiveTest {
        List<Archive> initData() {
            Archive archive = new Archive(5L , "title-test5" , "2021" , "07/25");
            Archive archive2 = new Archive(4L , "title-test4" , "2021" , "04/11");
            Archive archive3 = new Archive(3L , "title-test3" , "2020" , "11/15");
            Archive archive4 = new Archive(2L , "title-test2" , "2020" , "10/31");
            Archive archive5 = new Archive(1L , "title-test" , "2019" , "10/28");

            return List.of(archive , archive2 , archive3 , archive4 , archive5);
        }

        @Test
        @DisplayName("같은 년도에 있을때는 ArciveYear List가 한개가 나와야한다")
        void archiveTest() {
            // given
            // 2021년도 게시글 4개
            Archive archive = new Archive(4L , "title-test4" , "2021" , "12/11");
            Archive archive2 = new Archive(3L , "title-test3" , "2021" , "11/15");
            Archive archive3 = new Archive(2L , "title-test2" , "2021" , "10/31");
            Archive archive4 = new Archive(1L , "title-test" , "2021" , "10/28");

            List<Archive> archives = List.of(archive , archive2 , archive3 , archive4);

            // when
            when(postsRepository.findPostsArchive())
                    .thenReturn(archives);

            // then
            List<ArchiveByYear> archiveByYears = postsService.getPostsArchive();
            assertThat(archiveByYears.size()).isEqualTo(1);

            ArchiveByYear archiveByYear = archiveByYears.get(0);
            assertThat(archiveByYear.getCreatedYear()).isEqualTo("2021");
            assertThat(archiveByYear.getArchives().size()).isEqualTo(4);
        }

        @Test
        @DisplayName("다른 년도에 있을때는 ArciveYear 연도의 개수만큼 나와야한다")
        void archiveTest2() {
            // given
            // 2021년도 게시글 4개
            List<Archive> archives = this.initData();

            // when
            when(postsRepository.findPostsArchive())
                    .thenReturn(archives);

            // then
            List<ArchiveByYear> archiveByYears = postsService.getPostsArchive();
            assertThat(archiveByYears.size()).isEqualTo(3);
        }

        @Test
        @DisplayName("다른 년도에 있을때는 ArciveYear 연도의 개수만큼 나와야하고 , 연도 , 날짜 내림차순으로 정렬되어야한다")
        void archiveTest3() {
            // given
            // 2021년도 게시글 4개
            List<Archive> archives = this.initData();

            // when
            when(postsRepository.findPostsArchive())
                    .thenReturn(archives);

            // then
            List<ArchiveByYear> archiveByYears = postsService.getPostsArchive();
            assertThat(archiveByYears.size()).isEqualTo(3);

            assertThat(archiveByYears.get(0).getCreatedYear()).isEqualTo("2021");
            assertThat(archiveByYears.get(1).getCreatedYear()).isEqualTo("2020");
            assertThat(archiveByYears.get(2).getCreatedYear()).isEqualTo("2019");

            assertThat(archiveByYears.get(0).getCreatedYear()).isGreaterThan(archiveByYears.get(1).getCreatedYear());
            assertThat(archiveByYears.get(0).getCreatedYear()).isGreaterThan(archiveByYears.get(2).getCreatedYear());
            assertThat(archiveByYears.get(1).getCreatedYear()).isGreaterThan(archiveByYears.get(2).getCreatedYear());

            ArchiveByYear archive_2021 = archiveByYears.get(0);
            ArchiveByYear archive_2020 = archiveByYears.get(1);

            assertThat(archive_2021.getArchives().get(0).getCreatedDate())
                    .isGreaterThan(archive_2021.getArchives().get(1).getCreatedDate());
            assertThat(archive_2020.getArchives().get(0).getCreatedDate())
                    .isGreaterThan(archive_2020.getArchives().get(1).getCreatedDate());
        }

        @Test
        @DisplayName("각각의 연도가 개수에 맞게 나와야 한다")
        void archiveTest4() {
            // given
            // 2021년도 게시글 4개
            List<Archive> archives = this.initData();

            // when
            when(postsRepository.findPostsArchive())
                    .thenReturn(archives);

            // then
            List<ArchiveByYear> archiveByYears = postsService.getPostsArchive();
            assertThat(archiveByYears.size()).isEqualTo(3);

            assertThat(archiveByYears.get(0).getCreatedYear()).isEqualTo("2021");
            assertThat(archiveByYears.get(1).getCreatedYear()).isEqualTo("2020");
            assertThat(archiveByYears.get(2).getCreatedYear()).isEqualTo("2019");

            assertThat(archiveByYears.get(0).getArchives().size()).isEqualTo(2);
            assertThat(archiveByYears.get(1).getArchives().size()).isEqualTo(2);
            assertThat(archiveByYears.get(2).getArchives().size()).isEqualTo(1);
        }
    }
}

final class PostsTestDomain {
    static final Posts 널_게시글 = null;
    static final Posts posts = Posts.builder()
            .id(1L)
            .email("test")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts2 = Posts.builder()
            .id(2L)
            .email("test2")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts3 = Posts.builder()
            .id(3L)
            .email("test3")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Posts posts4 = Posts.builder()
            .id(4L)
            .email("test4")
            .postsTagsMappingList(Collections.emptyList())
            .build();

    static final Tags tags = Tags.builder()
            .id(1L)
            .name("tag1")
            .build();

    static final Tags tags2 = Tags.builder()
            .id(2L)
            .name("tag2")
            .build();

    static final PostsTagsMapping postsTagsMapping = PostsTagsMapping.builder()
            .id(1L)
            .posts(posts)
            .tags(tags)
            .build();

    static final PostsTagsMapping postsTagsMapping2 = PostsTagsMapping.builder()
            .id(1L)
            .posts(posts2)
            .tags(tags)
            .build();

    static final PostsTagsMapping postsTagsMapping3 = PostsTagsMapping.builder()
            .id(3L)
            .posts(posts3)
            .tags(tags)
            .build();


    static final PostsCard firstPostsCard = PostsCard.builder()
            .id(1L)
            .title("first-test-title")
            .bannerImage("first-test-image")
            .build();

    static final PostsCard secondPostsCard = PostsCard.builder()
            .id(2L)
            .title("second-test-title2")
            .bannerImage("second-test-image2")
            .build();

    static final PostsCard thirdPostsCard = PostsCard.builder()
            .id(3L)
            .title("third-test-title3")
            .bannerImage("third-test-image3")
            .build();


    static final PageRequest pageRequest = PageRequest.of(0 , 5);

    static final List<Posts> 게시글_리스트 = List.of(posts , posts2 , posts3 , posts4);
}