package com.example.videogamelibrary.ratelimiter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestRateLimitingInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RequestRateLimitingInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        // Берём IP клиента; для токена берите request.getHeader("Authorization") или другой идентификатор
        String clientId = request.getRemoteAddr();

        if (!rateLimiterService.isAllowed(clientId)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("429 Too Many Requests: предел в 5 запросов/минуту исчерпан");
            return false;  // запрос не пойдёт дальше к контроллеру
        }
        return true;  // разрешаем
    }
}

