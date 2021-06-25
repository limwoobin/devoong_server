package com.drogbalog.server.domain.tags.domain.entity;

import com.drogbalog.server.domain.posts.domain.entity.PostsTagsMapping;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags")
public class Tags extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private Long id;

    @Column(nullable = false , length = 30)
    private String name;

    @Column(nullable = false , length = 10)
    private Status status;

    @OneToMany(mappedBy = "tags")
    private List<PostsTagsMapping> postsTagsMappingList;

    @Builder
    public Tags(Long id , String name) {
        this.id = id;
        this.name = name;
    }
}
