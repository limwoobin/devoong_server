package com.drogbalog.server.global.config.security.oauth;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.global.code.AuthProvider;
import com.drogbalog.server.global.config.security.UserPrincipal;
import com.drogbalog.server.global.config.security.oauth.user.OAuth2UserInfo;
import com.drogbalog.server.global.config.security.oauth.user.OAuth2UserInfoFactory;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import com.drogbalog.server.global.exception.auth.AuthExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest , oAuth2User);
        } catch (AuthenticationException ex) {
            throw new UnAuthorizedException(AuthExceptionCode.OAUTH2.getCode() , "OAuth2 Authentication is failed.");
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest , OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId() , oAuth2User.getAttributes());

        String email = oAuth2UserInfo.getEmail();
        if (StringUtils.isEmpty(email)) {
            throw new UnAuthorizedException(AuthExceptionCode.OAUTH2.getCode() , "OAuth2 Authentication is failed.");
        }

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                return null;
            }
            user = updateExistingUser(user , oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest , oAuth2UserInfo);
        }

        return UserPrincipal.create(user , oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest , OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .providerId(oAuth2UserInfo.getId())
                .email(oAuth2UserInfo.getEmail())
                .nickname(oAuth2UserInfo.getNickName())
                .imageUri(oAuth2UserInfo.getImageUri())
                .build();

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser , OAuth2UserInfo oAuth2UserInfo) {
        existingUser.update(oAuth2UserInfo.getNickName() , oAuth2UserInfo.getImageUri());
        return userRepository.save(existingUser);
    }
}
