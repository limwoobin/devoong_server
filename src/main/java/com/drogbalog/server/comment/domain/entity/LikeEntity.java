package com.drogbalog.server.comment.domain.entity;

import com.drogbalog.server.user.domain.entity.UserEntity;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "like")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(length = 20)
    private long commentId;

    @Column(length = 20)
    private long childCommentId;

    @Column(length = 20)
    private long userId;

    @OneToOne
    private UserEntity userEntity;
}
