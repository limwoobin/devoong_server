package com.drogbalog.server.comment.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "child_comment")
public class ChildCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(length = 20)
    private long commentId;

    @Column(length = 30)
    private String email;

    @Column(length = 200)
    private String comment;

    @Column(nullable = false , updatable = true)
    @CreatedDate
    private LocalDateTime createdAt;

//    private CommentEntity commentEntity;
}
