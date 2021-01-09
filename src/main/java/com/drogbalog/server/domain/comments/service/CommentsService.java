package com.drogbalog.server.domain.comments.service;

import com.drogbalog.server.domain.comments.dao.CommentsDao;
import com.drogbalog.server.domain.comments.domain.response.CommentsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsDao commentsDao;

    public List<CommentsResponse> getCommentsByPosts(long postsId) {
        List<CommentsResponse> commentsResponseList = commentsDao.getCommentsByPosts(postsId);
        return commentsResponseList;
    }

    public List<CommentsResponse> getCommentsByUser(long userId) {
        List<CommentsResponse> commentsResponseList = commentsDao.getCommentsByUsers(userId);
        return commentsResponseList;
    }
}
