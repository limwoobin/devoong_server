package com.drogbalog.server.domain.user.domain.entity;

import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.Role;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
//@Getter
@Data
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , length = 20)
    private Long id;

    @Column(length = 30)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(length = 20)
    private String nickname;

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
    public User(String email , String password , String nickname , String profileImagePath , Gender gender , Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImagePath = profileImagePath;
        this.gender = gender;
        this.status = Status.ACTIVE;
        this.role = role;
    }

    public User update(String nickname , String profileImagePath) {
        this.nickname = nickname;
        this.profileImagePath = profileImagePath;

        return this;
    }

    public User update(UserRequest request) {
        this.password = request.getPassword();
        this.nickname = request.getNickname();
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
