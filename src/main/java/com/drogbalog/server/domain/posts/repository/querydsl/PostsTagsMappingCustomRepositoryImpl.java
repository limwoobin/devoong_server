package com.drogbalog.server.domain.posts.repository.querydsl;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingCustomRepository;
import com.drogbalog.server.global.code.Status;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.drogbalog.server.domain.posts.domain.entity.QPostsTagsMapping.postsTagsMapping;

@RequiredArgsConstructor
public class PostsTagsMappingCustomRepositoryImpl implements PostsTagsMappingCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostsResponse> findAllByTagsId(Pageable pageable, Long tagsId) {
        QueryResults<PostsResponse> results = queryFactory
                .select(Projections.constructor(PostsResponse.class,
                        postsTagsMapping.posts.id,
                        postsTagsMapping.posts.subject,
                        postsTagsMapping.posts.contents,
                        postsTagsMapping.posts.createdDate))
                .from(postsTagsMapping)
                .where(postsTagsMapping.tags.id.eq(tagsId)
                .and(postsTagsMapping.posts.status.eq(Status.ACTIVE)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults() , pageable , results.getTotal());
    }
}
