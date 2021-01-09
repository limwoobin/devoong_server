package com.drogbalog.server.domain.user.domain.entity;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
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
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private long id;

    @Column(length = 30)
    private String email;

    @Column(length = 200)
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
    public User(String email , String password , String nickName , String profileImagePath , Gender gender , Status status , Role role) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.profileImagePath = profileImagePath;
        this.gender = gender;
        this.status = status;
        this.role = role;
    }

    public User update(String nickName , String profileImagePath) {
        this.nickName = nickName;
        this.profileImagePath = profileImagePath;

        return this;
    }

    public User update(UserRequest request) {
        this.password = request.getPassword();
        this.nickName = request.getNickName();
        this.profileImagePath = request.getProfileImagePath();

        return this;
    }

    public User updateStatus(Status status) {
        this.status = status;

        return this;
    }

    public String getRoleDescription() {
        return this.role.getDescription();
    }
}
