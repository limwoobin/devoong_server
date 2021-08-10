package com.drogbalog.server.global.config.web;

import com.drogbalog.server.global.config.security.jwt.JwtAuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtAuthenticationInterceptor interceptor;

    private static final List<String> EXCLUDE_URL =
            List.of("/test", "/test_db", "/swagger-ui.html", "/auth/**");
    private static final long MAX_SECONDS = 3600;

    @Value("${client.domain}")
    private String client;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(client)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_SECONDS);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/users/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns(EXCLUDE_URL);
    }
}
