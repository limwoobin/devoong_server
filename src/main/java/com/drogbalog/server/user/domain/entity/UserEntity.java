package com.drogbalog.server.user.domain.entity;

import com.drogbalog.server.global.code.Gender;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(length = 20)
    private String nickName;

    @Column(length = 100)
    private String profileImagePath;

    @Column(length = 6)
    private Gender gender;

    @Column(nullable = false , updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "credit" , cascade = CascadeType.PERSIST)
    private List<UserHistoryEntity> userHistory;
}
