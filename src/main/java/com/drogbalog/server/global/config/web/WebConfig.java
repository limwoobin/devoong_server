package com.drogbalog.server.global.config.web;

import com.drogbalog.server.global.config.security.auth.LoginUserArgumentResolver;
import com.drogbalog.server.global.config.security.jwt.JwtAuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    private final JwtAuthenticationInterceptor interceptor;

    private static final List<String> EXCLUDE_URL =
            Collections.unmodifiableList(Arrays.asList("/test" , "/test_db" , "/swagger-ui.html" , "/auth/**"));
    private static final long MAX_SECONDS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(Arrays.toString(MethodType.values()))
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(MAX_SECONDS);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/users/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns(EXCLUDE_URL);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}
