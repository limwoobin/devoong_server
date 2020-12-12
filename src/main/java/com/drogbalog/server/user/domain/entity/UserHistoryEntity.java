package com.drogbalog.server.user.domain.entity;

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
@Table(name = "user_history")
public class UserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private long id;

    @Column(name = "user_id" , nullable = false , updatable = false)
    private long user_id;

    @Column(nullable = false , updatable = false)
    @CreatedDate
    private LocalDateTime loginAt;
}
