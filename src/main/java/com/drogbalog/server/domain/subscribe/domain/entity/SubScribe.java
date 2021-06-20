package com.drogbalog.server.domain.subscribe.domain.entity;

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
@Table(name = "subscribe")
public class SubScribe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private Long id;

    @Column(nullable = false , length = 30)
    private String email;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    public void active() {
        this.status = Status.ACTIVE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }

    @Builder
    public SubScribe(String email) {
        this.email = email;
        this.status = Status.ACTIVE;
    }
}
