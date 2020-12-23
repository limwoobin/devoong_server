package com.drogbalog.server.comment.dao;

import com.drogbalog.server.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentDao {
    private final CommentRepository repository;
}
