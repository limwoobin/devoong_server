package com.drogbalog.server.domain.comments.dao;

import com.drogbalog.server.domain.comments.converter.CommentsConverter;
import com.drogbalog.server.domain.comments.domain.entity.Comments;
import com.drogbalog.server.domain.comments.domain.response.CommentsResponse;
import com.drogbalog.server.domain.comments.repository.CommentsRepository;
import com.drogbalog.server.domain.comments.repository.SubCommentsRepository;
import com.drogbalog.server.global.code.OrderByType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentsDao {
    private final CommentsRepository commentsRepository;
    private final SubCommentsRepository subCommentsRepository;
    private final CommentsConverter converter;

    public List<CommentsResponse> getCommentsByPosts(long postsId) {
        List<Comments> comments = commentsRepository.findAllByPostsIdOrderByIdDesc(postsId);
        return converter.convertComments(comments);
    }

    public List<CommentsResponse> getCommentsByUsers(long userId) {
        List<Comments> comments = commentsRepository.findAllByUserIdOrderByIdDesc(userId);
        return converter.convertComments(comments);
    }
}
