package com.drogbalog.server.domain.posts.domain.entity;

import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(nullable = false , length = 30)
    private String email;

    @Column(nullable = false , length = 50)
    private String subject;

    @Lob
    private String contents;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private BigInteger views;

    @Builder
    public Posts(String email , String subject , String contents) {
        this.email = email;
        this.subject = subject;
        this.contents = contents;
        this.status = Status.ACTIVE;
    }

    public void update(String subject , String contents) {
        this.subject = subject;
        this.contents = contents;
    }

    public Posts updateStatus(Status status) {
        this.status = status;
        return this;
    }

    public void addPostsViews() {
        this.views = views.add(BigInteger.ONE);
    }
}
