package com.drogbalog.server.domain.posts.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategories is a Querydsl query type for Categories
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategories extends EntityPathBase<Categories> {

    private static final long serialVersionUID = -28008109L;

    public static final QCategories categories = new QCategories("categories");

    public final com.drogbalog.server.global.entity.QBaseTimeEntity _super = new com.drogbalog.server.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<Posts, QPosts> postsList = this.<Posts, QPosts>createList("postsList", Posts.class, QPosts.class, PathInits.DIRECT2);

    public final EnumPath<com.drogbalog.server.global.code.Status> status = createEnum("status", com.drogbalog.server.global.code.Status.class);

    public QCategories(String variable) {
        super(Categories.class, forVariable(variable));
    }

    public QCategories(Path<? extends Categories> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategories(PathMetadata metadata) {
        super(Categories.class, metadata);
    }

}

