package com.drogbalog.server.domain.posts.dao;

import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.querydsl.PostsRepositorySupport;
import com.drogbalog.server.domain.tags.domain.entity.Tags;
import com.drogbalog.server.domain.tags.mapper.TagsMapper;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsDao {
    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;
    private final PostsRepositorySupport postsRepositorySupport;
    private final TagsMapper tagsMapper;

    @Transactional
    public Page<PostsResponse> findAll(Pageable pageable) {
        Page<Posts> postsEntities = postsRepository.findAll(pageable);
        return postsEntities.map(postsMapper::toPostsResponse);
    }

    @Transactional
    public Page<PostsResponse> findAllByTagsId(Pageable pageable , long tagsId) {
        Page<PostsResponse> postsResponsePage = postsRepositorySupport.findAllByTagsId(pageable , tagsId);
        return postsResponsePage;
    }

    @Transactional
    public PostsResponse findById(long postsId) {
        this.addPostsViews(postsId);
        Posts posts = postsRepository.findById(postsId).get();
        PostsResponse postsResponse = postsMapper.toPostsResponse(posts);

        List<Tags> tagsList = posts.getPostsTagsMappingList()
                .stream()
                .map(PostsTagsMapping::getTags)
                .collect(Collectors.toList());
        postsResponse.addTagsList(tagsMapper.toTagResponseList(tagsList));
        return postsResponse;
    }

    public void addPostsViews(long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));
        posts.addPostsViews();
        postsRepository.save(posts);
    }

    @Transactional
    public Page<PostsResponse> searchAll(Pageable pageable , String keyword) {
        return postsRepositorySupport.searchAllResponse(pageable , keyword);
    }
}
