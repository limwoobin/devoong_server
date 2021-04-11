package com.drogbalog.server.domain.posts.repository.querydsl;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.global.code.Status;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.drogbalog.server.domain.posts.domain.entity.QPosts.posts;

/**
 * Created by Drogba on 2021-02-05
 * github : https://github.com/limwoobin
 */

@Repository
@RequiredArgsConstructor
public class PostsRepositorySupport  {
    private final JPAQueryFactory queryFactory;

    public Page<PostsResponse> searchAllResponse(PageRequest pageRequest , String keyword) {
            QueryResults<PostsResponse> postsResponses = queryFactory
                .from(posts)
                .select(Projections.constructor(PostsResponse.class))
                .where(posts.subject.contains(keyword)
                                .or(posts.contents.contains(keyword))
                        ,posts.status.eq(Status.ACTIVE))
                .orderBy(posts.id.desc())
                .fetchResults();

            return new PageImpl<>(postsResponses.getResults() , pageRequest , postsResponses.getTotal());
    }

    public Page<PostsResponse> findAllByTagsId(PageRequest pageRequest , List<Long> postsIds) {
            QueryResults<PostsResponse> postsResponse = queryFactory
                .from(posts)
                .select(Projections.constructor(PostsResponse.class))
                .where(posts.id.in(postsIds))
                .orderBy(posts.id.desc())
                .fetchResults();

            return new PageImpl<>(postsResponse.getResults() , pageRequest , postsResponse.getTotal());
    }
}
