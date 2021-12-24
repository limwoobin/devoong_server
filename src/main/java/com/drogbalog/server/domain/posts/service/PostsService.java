package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.dto.Archive;
import com.drogbalog.server.domain.posts.domain.dto.ArchiveByYear;
import com.drogbalog.server.domain.posts.domain.dto.PostsCardList;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.mapper.TagsMapper;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import com.drogbalog.server.infra.retrofit.GithubApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsTagsMappingRepository postsTagsMappingRepository;
    private final PostsMapper postsMapper = PostsMapper.INSTANCE;
    private final TagsMapper tagsMapper = TagsMapper.INSTANCE;
    private final GithubApiClient<String> githubApiClient;

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsList(Pageable pageable) {
        List<Posts> postsList = postsRepository.findAllPostsAndTags(pageable);
        List<PostsResponse> postsResponseList = new ArrayList<>(postsList.size());

        for (Posts posts : postsList) {
            List<Tags> tagsList = posts.getPostsTagsMappingList()
                    .stream()
                    .map(PostsTagsMapping::getTags)
                    .collect(Collectors.toList());

            PostsResponse postsResponse = postsMapper.converts(posts);
            postsResponse.setTagsResponseList(tagsMapper.toTagResponseList(tagsList));
            postsResponseList.add(postsResponse);
        }

        return new PageImpl<>(postsResponseList , pageable , postsRepository.findAllPostsCount());
    }

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsListByTagsName(Pageable pageable , String name) {
        return postsTagsMappingRepository.findAllByTagsName(pageable, name);
    }

    @Transactional(readOnly = true)
    public PostsResponse getPosts(Long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));

        PostsResponse postsResponse = postsMapper.converts(posts);
        postsResponse.addTagsList(postsTagsMappingRepository.findTagsByPostsId(postsId));
        postsResponse.addPreviousAndNextPostsCard(getPostsCardList(postsId));

        String markdownData = githubApiClient.callMarkdownApi(posts.getContents());
        postsResponse.setContents(markdownData);

        return postsResponse;
    }

    public PostsCardList getPostsCardList(Long postsId) {
        List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(postsId);
        return new PostsCardList(postsCards , postsId);
    }

    @Transactional(readOnly = true)
    public List<PostsResponse> getLatestPosts() {
        List<Posts> latestPosts = postsRepository.findTop3ByStatusOrderByIdDesc(Status.ACTIVE);
        return postsMapper.convertList(latestPosts);
    }

    @Transactional(readOnly = true)
    public List<ArchiveByYear> getPostsArchive() {
        List<Archive> archives = postsRepository.findPostsArchive();

        Map<String , List<Archive>> archiveMaps = archives.stream()
                .collect(Collectors.groupingBy(Archive::getCreatedYear));

        return createSortedArchiveDto(archiveMaps);
    }

    private List<ArchiveByYear> createSortedArchiveDto(Map<String , List<Archive>> archiveMaps) {
        List<ArchiveByYear> archiveByYears = new ArrayList<>();

        for (Map.Entry<String , List<Archive>> elem : archiveMaps.entrySet()) {
            ArchiveByYear archiveByYear = new ArchiveByYear(elem.getKey() , elem.getValue());
            archiveByYears.add(archiveByYear);
        }

        Collections.sort(archiveByYears);
        return archiveByYears;
    }
}
