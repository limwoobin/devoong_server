package com.drogbalog.server.domain.user.domain.entity;

import com.drogbalog.server.global.code.AuthProvider;
import com.drogbalog.server.global.code.Gender;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.Role;
import com.drogbalog.server.global.entity.BaseTimeEntity;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import javax.validation.constraints.Email;

@EqualsAndHashCode(callSuper = true)
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

    @Email
    @Column(length = 30)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(length = 20)
    private String nickname;

    @Column(length = 100)
    private String imageUri;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 8)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(length = 10)
    private String providerId;

    @Builder
    public User(String email , String password , String nickname , String imageUri
            , Gender gender , Role role , AuthProvider provider , String providerId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imageUri = imageUri;
        this.gender = gender;
        this.status = Status.ACTIVE;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void update(String nickname , String imageUri) {
        this.nickname = nickname;
        this.imageUri = imageUri;

    }

    public void update(UserRequest request) {
        this.password = request.getPassword();
        this.nickname = request.getNickname();
        this.imageUri = request.getImageUri();

    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public String getRoleDescription() {
        return this.role.getDescription();
    }
}
