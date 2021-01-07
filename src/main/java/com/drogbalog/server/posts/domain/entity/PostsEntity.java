package com.drogbalog.server.posts.domain.entity;

import com.drogbalog.server.global.code.PostType;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class PostsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(length = 30)
    private String email;

    @Column(length = 50)
    private String subject;

    @Column(length = 1000)
    private String contents;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Builder
    public PostsEntity(String email , String subject , String contents) {
        this.email = email;
        this.subject = subject;
        this.contents = contents;
    }

    public PostsEntity update(String subject , String contents) {
        this.subject = subject;
        this.contents = contents;

        return this;
    }
}
