package com.drogbalog.server.global.config.security.jwt;

import com.drogbalog.server.global.exception.auth.AuthExceptionCode;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String , Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            checkLogoutToken(token);

            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        }

        return false;
    }

    private void checkLogoutToken(String accessToken) {
        if (redisTemplate.opsForValue().get(accessToken) != null) {
            throw new UnAuthorizedException(AuthExceptionCode.LOGOUT.getCode() , "Already Logout Token");
        }
    }
}
