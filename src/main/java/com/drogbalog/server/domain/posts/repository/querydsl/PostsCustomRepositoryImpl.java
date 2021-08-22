package com.drogbalog.server.domain.posts.repository.querydsl;

import com.drogbalog.server.domain.posts.domain.dto.Archive;
import com.drogbalog.server.domain.posts.domain.entity.QPosts;
import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
import com.drogbalog.server.domain.posts.repository.PostsCustomRepository;
import com.drogbalog.server.global.code.Status;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.drogbalog.server.domain.posts.domain.entity.QPosts.posts;

/**
 * Created by Drogba on 2021-02-05
 * github : https://github.com/limwoobin
 */

@Repository
@RequiredArgsConstructor
public class PostsCustomRepositoryImpl implements PostsCustomRepository {
    private final JPAQueryFactory queryFactory;

    public Page<PostsResponse> searchAllResponse(Pageable pageable , String keyword) {
        QueryResults<PostsResponse> postsResponseList = queryFactory
            .select(Projections.constructor(PostsResponse.class,
                    posts.id,
                    posts.title,
                    posts.contents,
                    posts.views,
                    posts.createdDate
            ))
            .from(posts)
            .where(posts.title.contains(keyword)
            .and(posts.status.eq(Status.ACTIVE)))
            .orderBy(posts.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        return new PageImpl<>(postsResponseList.getResults() , pageable , postsResponseList.getTotal());
    }

    @Override
    public List<PostsCard> findPreviousAndNextPostsCardById(Long id) {
        QPosts qPosts = posts;

        Long previousId = queryFactory.select(qPosts.id.max())
                .from(qPosts)
                .where(qPosts.id.lt(id))
                .fetchOne();

        Long nextId = queryFactory.select(qPosts.id.min())
                .from(qPosts)
                .where(qPosts.id.gt(id))
                .fetchOne();

        return queryFactory.select(Projections.constructor(PostsCard.class,
                qPosts.id,
                qPosts.title,
                qPosts.bannerImage))
                .from(qPosts)
                .where(qPosts.id.in(previousId , id , nextId))
                .orderBy(qPosts.id.asc())
                .fetch();
    }

    @Override
    public List<Archive> findPostsArchive() {
        QPosts qPosts = posts;
        StringTemplate formattedYear = Expressions.stringTemplate(
                "DATE_FORMAT({0} , {1})"
                ,qPosts.createdDate
                ,ConstantImpl.create("%Y"));

        StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0} , {1})"
                ,qPosts.createdDate
                ,ConstantImpl.create("%m-%d"));

        return queryFactory.select(Projections.constructor(Archive.class,
                qPosts.id,
                qPosts.title,
                formattedYear,
                formattedDate))
                .from(qPosts)
                .orderBy(qPosts.id.desc())
                .orderBy(formattedDate.desc())
                .fetch();
    }
}