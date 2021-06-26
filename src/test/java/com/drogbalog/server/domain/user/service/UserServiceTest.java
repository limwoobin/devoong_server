package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.domain.response.JwtResponse;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.security.Role;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.drogbalog.server.domain.user.service.UserTestDomain.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@DisplayName("유저 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserValidator userValidator;
    @Mock private UserRepository userRepository;
    @Mock private JwtTokenProvider jwtTokenProvider;
    @Mock private RedisTemplate<String , Object> redisTemplate;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("유저 인증 관련 테스트")
    class UserAuthTest {
        @Test
        @DisplayName("유저 회원가입 테스트")
        public void signIn_test() {
            // given
            UserRequest userRequest = 회원가입_신청한_유저;

            // when
            when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("123123123");
            when(userRepository.save(any())).thenReturn(회원가입된_유저_ENTITY);

            // then
            UserResponse userResponse = userService.signUp(userRequest);
            assertThat(userResponse.getEmail()).isEqualTo(회원가입된_유저_ENTITY.getEmail());
            assertThat(userResponse.getNickname()).isEqualTo(회원가입된_유저_ENTITY.getNickname());
        }

        @Test
        @DisplayName("로그인 테스트")
        public void login_test() {
            // given
            UserRequest userRequest = 유저;
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setAccessToken("accessToken123");
            jwtResponse.setRefreshToken("refreshToken123");

            // when
            when(passwordEncoder.matches(any() , any())).thenReturn(true);
            when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.ofNullable(유저_ENTITY));
            when(jwtTokenProvider.generateTokens(any())).thenReturn(jwtResponse);

            // then
            UserResponse userResponse = userService.login(userRequest);
            assertThat(userResponse.getEmail()).isEqualTo(userRequest.getEmail());
            assertThat(userResponse.getNickname()).isEqualTo(userRequest.getNickname());
            assertThat(userResponse.getJwtResponse()).isEqualTo(jwtResponse);
        }
    }
}

final class UserTestDomain {
    static final UserRequest 회원가입_신청한_유저 = UserRequest.builder()
            .email("test@naver.com")
            .password("woobeen123")
            .nickname("drogba")
            .status(Status.ACTIVE)
            .role(Role.USER)
            .build();

    static final User 회원가입_신청한_유저_ENTITY = User.builder()
            .email("test@naver.com")
            .password("123123123")
            .nickname("drogba")
            .imageUri("testPath")
            .role(Role.USER)
            .build();

    static final User 회원가입된_유저_ENTITY = User.builder()
            .id(1L)
            .email("test@naver.com")
            .password("123123123")
            .nickname("drogba")
            .imageUri("testPath")
            .role(Role.USER)
            .build();

    static final UserRequest 유저 = UserRequest.builder()
            .email("test@naver.com")
            .password("woobeen123")
            .nickname("drogba")
            .status(Status.ACTIVE)
            .role(Role.USER)
            .build();

    static final User 유저_ENTITY = User.builder()
            .id(1L)
            .email("test@naver.com")
            .password("123123123")
            .nickname("drogba")
            .imageUri("testPath")
            .role(Role.USER)
            .build();
}