package com.drogbalog.server.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {
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

}
