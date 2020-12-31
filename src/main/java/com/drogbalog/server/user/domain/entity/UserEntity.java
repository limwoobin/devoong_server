package com.drogbalog.server.user.domain.entity;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public UserEntity(String email , String nickName , String profileImagePath , Role role) {
        this.email = email;
        this.nickName = nickName;
        this.profileImagePath = profileImagePath;
        this.role = role;
    }

    public UserEntity update(String nickName , String profileImagePath) {
        this.nickName = nickName;
        this.profileImagePath = profileImagePath;

        return this;
    }

    public String getRoleDescription() {
        return this.role.getDescription();
    }
}
