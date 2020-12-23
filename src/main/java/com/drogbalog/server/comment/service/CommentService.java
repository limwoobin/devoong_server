package com.drogbalog.server.comment.service;

import com.drogbalog.server.comment.dao.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
}
