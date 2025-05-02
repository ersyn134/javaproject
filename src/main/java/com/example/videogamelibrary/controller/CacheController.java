package com.example.videogamelibrary.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final RedisTemplate<String, Object> redis;

    public CacheController(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    @GetMapping("/videogames")
    public ResponseEntity<List<Object>> showVideoGamesCache() {
        Set<String> keys = redis.keys("videogames::*");
        if (keys == null || keys.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<Object> list = keys.stream()
            .map(redis.opsForValue()::get)
            .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
