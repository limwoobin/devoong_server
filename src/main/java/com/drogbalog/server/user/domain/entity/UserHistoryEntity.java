package com.drogbalog.server.user.domain.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_history")
public class UserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false , length = 20)
    private long id;

    @Column(name = "user_id" , nullable = false , length = 20)
    private long userId;

    @Column(nullable = false , updatable = false)
    @CreatedDate
    private LocalDateTime loginAt;
}
