package com.drogbalog.server.domain.user.service;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.domain.response.JwtResponse;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.domain.user.service.validator.UserValidator;
import com.drogbalog.server.domain.user.service.validator.impl.EmailValidator;
import com.drogbalog.server.domain.user.service.validator.impl.NickNameValidator;
import com.drogbalog.server.global.config.security.Role;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.global.exception.DuplicateDataException;
import com.drogbalog.server.global.exception.UserNotFoundException;
import com.drogbalog.server.global.exception.messages.CommonExceptionType;
import com.drogbalog.server.global.exception.messages.DuplicateExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.drogbalog.server.domain.user.service.UserTestDomain.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@DisplayName("유저 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private JwtTokenProvider jwtTokenProvider;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock UserValidator userValidator;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("유저 유효성 테스트")
    class UserValidateTest {
        @Mock UserRepository userRepository;
        @InjectMocks EmailValidator emailValidator;
        @InjectMocks NickNameValidator nickNameValidator;

        @Test
        @DisplayName("이미 존재하는 이메일이 있으면 DuplicateDataException 이 발생해야 한다")
        void alreadyExistEmail_exception() {
            // given
            UserRequest userRequest = 회원가입_신청한_유저;

            // when
            when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.ofNullable(회원가입된_유저_ENTITY));

            // then
            assertThatThrownBy(() -> {
                emailValidator.execute(userRequest);
            }).isInstanceOf(DuplicateDataException.class).hasMessageContaining(DuplicateExceptionType.EMAIL_DUPLICATED.getMessage());
        }

        @Test
        @DisplayName("이미 존재하는 닉네임이 있으면 DuplicateDataException 이 발생해야 한다")
        void alreadyExistNickname_exception() {
            // given
            UserRequest userRequest = 회원가입_신청한_유저;

            // when
            when(userRepository.findByNickname(userRequest.getNickname())).thenReturn(Optional.ofNullable(회원가입된_유저_ENTITY));

            assertThatThrownBy(() -> {
                nickNameValidator.execute(userRequest);
            }).isInstanceOf(DuplicateDataException.class).hasMessageContaining(DuplicateExceptionType.NICKNAME_DUPLICATED.getMessage());
        }
    }

    @Nested
    @DisplayName("유저 인증 관련 테스트")
    class UserAuthTest {
        @Test
        @DisplayName("유저 회원가입 테스트")
        void signIn_test() {
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
        void login_test() {
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

    @Nested
    @DisplayName("유저 관련 테스트")
    class GetUserTest {
        @Test
        @DisplayName("유저 정보가 있으면 정상적으로 조회되어야 한다")
        void getUserInfo_test() {
            // given
            String email = 유저_ENTITY.getEmail();

            // when
            when(userRepository.findByEmail(email)).thenReturn(Optional.of(유저_ENTITY));

            // then
            UserResponse userResponse = userService.getUserInfo(email);
            assertThat(userResponse.getEmail()).isEqualTo(email);
        }

        @Test
        @DisplayName("유저 정보가 없으면 UserNotFoundException 이 발생되어야 한다")
        void getUserInfo_exception() {
            // given
            String 회원목록에_존재하지_않는_이메일 = "tet123@naver.com";

            // when
            when(userRepository.findByEmail(회원목록에_존재하지_않는_이메일)).thenReturn(Optional.empty());

            // then
            assertThatThrownBy(() -> userService.getUserInfo(회원목록에_존재하지_않는_이메일))
                    .isInstanceOf(UserNotFoundException.class)
                    .hasMessageContaining(CommonExceptionType.NOT_FOUND_USER.getMessage());
        }

        @Test
        @DisplayName("유저 정보를 업데이트 하면 정상적으로 변경되어야 한다")
        void update_test() {
            // given
            UserRequest userRequest = 업데이트_요청한_유저;
            userRequest.setId(1L);

            // when
            when(userRepository.findById(userRequest.getId())).thenReturn(Optional.of(유저_ENTITY_2));

            // then
            UserResponse userResponse = userService.updateUserInfo(userRequest);
            assertThat(userResponse.getNickname()).isEqualTo(업데이트_요청한_유저.getNickname());
        }
    }
}

final class UserTestDomain {
    static final UserRequest 회원가입_신청한_유저 = UserRequest.builder()
            .email("test@naver.com")
            .password("woobeen123")
            .nickname("drogba")
            .build();

    static final UserRequest 업데이트_요청한_유저 = UserRequest.builder()
            .email("test@naver.com")
            .password("123123123")
            .nickname("changedNickname")
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
            .build();

    static final User 유저_ENTITY = User.builder()
            .id(1L)
            .email("test@naver.com")
            .password("123123123")
            .nickname("drogba")
            .imageUri("testPath")
            .role(Role.USER)
            .build();

    static final User 유저_ENTITY_2 = User.builder()
            .id(1L)
            .email("test@naver.com")
            .password("123123123")
            .nickname("drogba02")
            .imageUri("testPath")
            .role(Role.USER)
            .build();
}