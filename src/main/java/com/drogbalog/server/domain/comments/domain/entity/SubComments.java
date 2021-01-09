package com.drogbalog.server.domain.comments.domain.entity;

import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

import static com.drogbalog.server.global.code.Status.ACTIVE;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sub_comments")
public class SubComments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(nullable = false , length = 20)
    private long commentsId;

    @Column(nullable = false , length = 1000)
    private String contents;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;
}
