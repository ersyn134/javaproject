package com.example.videogamelibrary.config;

import com.example.videogamelibrary.ratelimiter.RequestRateLimitingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestRateLimitingInterceptor rateLimitingInterceptor;

    public WebConfig(RequestRateLimitingInterceptor rateLimitingInterceptor) {
        this.rateLimitingInterceptor = rateLimitingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor)
                .addPathPatterns("/api/**"); // или конкретный endpoint, например "/api/library-items/**"
    }
}

