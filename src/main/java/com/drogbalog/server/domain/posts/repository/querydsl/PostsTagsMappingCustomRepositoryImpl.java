package com.drogbalog.server.domain.posts.repository.querydsl;

import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.PostsTagsMappingCustomRepository;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.global.code.Status;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.drogbalog.server.domain.posts.domain.entity.QPostsTagsMapping.postsTagsMapping;

@Repository
@RequiredArgsConstructor
public class PostsTagsMappingCustomRepositoryImpl implements PostsTagsMappingCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostsResponse> findAllByTagsName(Pageable pageable, String name) {
        QueryResults<PostsResponse> results = queryFactory
                .select(Projections.constructor(PostsResponse.class,
                        postsTagsMapping.posts.id,
                        postsTagsMapping.posts.title,
                        postsTagsMapping.posts.contents,
                        postsTagsMapping.posts.bannerImage,
                        postsTagsMapping.posts.views,
                        postsTagsMapping.posts.createdDate))
                .from(postsTagsMapping)
                .where(postsTagsMapping.tags.name.eq(name)
                .and(postsTagsMapping.posts.status.eq(Status.ACTIVE)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(postsTagsMapping.posts.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults() , pageable , results.getTotal());
    }

    @Override
    public List<TagsResponse> findTagsByPostsId(Long postsId) {
        return queryFactory
                .select(Projections.constructor(TagsResponse.class,
                        postsTagsMapping.tags.id,
                        postsTagsMapping.tags.name))
                .from(postsTagsMapping)
                .where(postsTagsMapping.posts.id.eq(postsId)
                .and(postsTagsMapping.tags.status.eq(Status.ACTIVE)))
                .fetch();
    }
}
