package com.drogbalog.server.domain.posts.domain.entity;

import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private Long id;

    @Column(nullable = false , length = 30)
    private String email;

    @Column(nullable = false , length = 50)
    private String title;

    @Column(nullable = false , length = 200)
    private String contents;

    @Column(length = 100)
    private String bannerImage;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Long views;

    @OneToMany(mappedBy = "posts")
    private List<PostsTagsMapping> postsTagsMappingList;

    @Builder
    public Posts(Long id , String email , String title , String contents , String bannerImage , List<PostsTagsMapping> postsTagsMappingList , LocalDateTime createdDate) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.contents = contents;
        this.bannerImage = bannerImage;
        this.status = Status.ACTIVE;
        this.postsTagsMappingList = postsTagsMappingList;
        this.createdDate = createdDate;
    }

    public void update(String title , String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void addPostsViews() {
        this.views++;
    }
}
