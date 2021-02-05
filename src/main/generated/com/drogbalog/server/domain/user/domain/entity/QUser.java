package com.drogbalog.server.domain.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 498382644L;

    public static final QUser user = new QUser("user");

    public final com.drogbalog.server.global.entity.QBaseTimeEntity _super = new com.drogbalog.server.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final EnumPath<com.drogbalog.server.global.code.Gender> gender = createEnum("gender", com.drogbalog.server.global.code.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUri = createString("imageUri");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final EnumPath<com.drogbalog.server.global.code.AuthProvider> provider = createEnum("provider", com.drogbalog.server.global.code.AuthProvider.class);

    public final StringPath providerId = createString("providerId");

    public final EnumPath<com.drogbalog.server.global.config.security.Role> role = createEnum("role", com.drogbalog.server.global.config.security.Role.class);

    public final EnumPath<com.drogbalog.server.global.code.Status> status = createEnum("status", com.drogbalog.server.global.code.Status.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

