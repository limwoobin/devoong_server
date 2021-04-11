package com.drogbalog.server.domain.posts.repository.querydsl;

import com.drogbalog.server.domain.posts.domain.entity.Posts;
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


import static com.drogbalog.server.domain.posts.domain.entity.QPosts.posts;
import static com.drogbalog.server.domain.posts.domain.entity.QPostsTagsMapping.postsTagsMapping;

/**
 * Created by Drogba on 2021-02-05
 * github : https://github.com/limwoobin
 */

@Repository
@RequiredArgsConstructor
public class PostsRepositorySupport  {
    private final JPAQueryFactory queryFactory;

    public Page<PostsResponse> searchAllResponse(PageRequest pageRequest , String keyword) {
        QueryResults<PostsResponse> postsResponseList = queryFactory
            .from(posts)
            .select(Projections.constructor(PostsResponse.class))
            .where(posts.subject.contains(keyword)
                            .or(posts.contents.contains(keyword))
                    ,posts.status.eq(Status.ACTIVE))
            .orderBy(posts.id.desc())
            .fetchResults();

        return new PageImpl<>(postsResponseList.getResults() , pageRequest , postsResponseList.getTotal());
    }

    public Page<PostsResponse> findAllByTagsId(PageRequest pageRequest , long tagsId) {
        QueryResults<PostsResponse> postsResponseList = queryFactory
                .from(posts)
                .select(Projections.constructor(PostsResponse.class))
                .where(posts.id.in(
                        queryFactory.from(postsTagsMapping)
                        .select(postsTagsMapping.posts.id)
                        .where(postsTagsMapping.tags.id.eq(tagsId))
                ))
                .orderBy(posts.id.desc())
                .fetchResults();

        return new PageImpl<>(postsResponseList.getResults() , pageRequest , postsResponseList.getTotal());
    }

    public Posts findById(long postsId) {
        return queryFactory
                .from(posts)
                .select(posts)
                .where(posts.id.eq(postsId))
                .fetchOne();
    }
}
