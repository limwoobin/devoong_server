package com.drogbalog.server.domain.posts.service;

import com.drogbalog.server.domain.posts.domain.dto.PostsCardList;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.mapper.PostsMapper;
import com.drogbalog.server.domain.posts.repository.PostsRepository;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingRepository;
import com.drogbalog.server.global.exception.EmptyDataException;
import com.drogbalog.server.global.exception.messages.EmptyDataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsTagsMappingRepository postsTagsMappingRepository;
    private final PostsMapper postsMapper = PostsMapper.INSTANCE;

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsList(Pageable pageable) {
        Page<Posts> posts = postsRepository.findAll(pageable);
        return posts.map(postsMapper::converts);
    }

    @Transactional(readOnly = true)
    public Page<PostsResponse> getPostsListByTagsId(Pageable pageable , Long tagsId) {
        return postsTagsMappingRepository.findAllByTagsId(pageable, tagsId);
    }

    @Transactional(readOnly = true)
    public PostsResponse getPosts(Long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new EmptyDataException(EmptyDataExceptionType.EMPTY_POSTS_DATA));

        PostsResponse postsResponse = postsMapper.converts(posts);
        postsResponse.addTagsList(postsTagsMappingRepository.findTagsByPostsId(postsId));
        postsResponse.addPreviousAndNextPostsCard(getPostsCardList(postsId));
        return postsResponse;
    }

    public PostsCardList getPostsCardList(Long postsId) {
        List<PostsCard> postsCards = postsRepository.findPreviousAndNextPostsCardById(postsId);
        return new PostsCardList(postsCards);
    }

    @Transactional(readOnly = true)
    public List<PostsResponse> getLatestPosts() {
        List<Posts> latestPosts = postsRepository.findTop3ByOrderByIdDesc();
        return postsMapper.convertList(latestPosts);
    }

    @Transactional(readOnly = true)
    public Page<PostsResponse> searchAll(Pageable pageable , String keyword) {
        return postsRepository.searchAllResponse(pageable , keyword);
    }
}
