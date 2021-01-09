package com.drogbalog.server.domain.posts.domain.entity;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.global.code.Status;
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
public class Posts extends BaseTimeEntity {

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
    private PostsType postsType;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Posts(String email , String subject , String contents) {
        this.email = email;
        this.subject = subject;
        this.contents = contents;
    }

    public Posts update(String subject , String contents) {
        this.subject = subject;
        this.contents = contents;

        return this;
    }

    public Posts updateStatus(Status status) {
        this.status = status;

        return this;
    }
}
