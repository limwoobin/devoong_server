package com.drogbalog.server.domain.posts.domain.entity;

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
@Table(name = "categories")
public class Categories extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(length = 20)
    private String name;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Categories(String name) {
        this.name = name;
        this.status = Status.ACTIVE;
    }

    public void update(String name , Status status) {
        this.name = name;
        this.status = status;

    }

    @OneToMany(mappedBy = "categories", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Posts> postsList;
}
