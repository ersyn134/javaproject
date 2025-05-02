package com.example.videogamelibrary.ratelimiter;

import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class RateLimiterService {
    // Храним для каждого клиента очередь отметок времени запросов
    private final Map<String, Deque<Long>> clientRequests = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 5;      // максимум запросов
    private static final long WINDOW_MILLIS = 60_000; // окно — 60 секунд

    /**
     * Проверяет, разрешён ли новый запрос для данного clientId.
     */
    public boolean isAllowed(String clientId) {
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = clientRequests
            .computeIfAbsent(clientId, id -> new ConcurrentLinkedDeque<>());

        synchronized (timestamps) {
            // Удаляем «старые» отметки
            while (!timestamps.isEmpty() && now - timestamps.peekFirst() > WINDOW_MILLIS) {
                timestamps.pollFirst();
            }
            if (timestamps.size() < MAX_REQUESTS) {
                timestamps.addLast(now);
                return true;
            } else {
                return false;
            }
        }
    }
}
