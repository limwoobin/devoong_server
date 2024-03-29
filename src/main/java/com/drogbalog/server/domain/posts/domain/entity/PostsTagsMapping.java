package com.drogbalog.server.domain.posts.domain.entity;

import com.drogbalog.server.domain.tags.domain.entity.Tags;
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
@Table(name = "posts_tags_mapping")
public class PostsTagsMapping extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_id")
    private Tags tags;

    @Builder
    public PostsTagsMapping(Long id , Posts posts , Tags tags) {
        this.id = id;
        this.posts = posts;
        this.tags = tags;
    }
}
