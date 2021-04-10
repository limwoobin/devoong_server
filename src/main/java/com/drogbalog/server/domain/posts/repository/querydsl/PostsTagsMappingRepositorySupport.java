package com.drogbalog.server.domain.posts.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostsTagsMappingRepositorySupport {
    private final JPAQueryFactory queryFactory;

}
