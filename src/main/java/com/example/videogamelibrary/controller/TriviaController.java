package com.example.videogamelibrary.controller;

import com.example.videogamelibrary.dto.TriviaQuestion;
import com.example.videogamelibrary.service.TriviaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    // Endpoint для получения викторины по видеоиграм
    // GET /api/trivia?amount=10
    @GetMapping
    public ResponseEntity<List<TriviaQuestion>> getTrivia(@RequestParam(value = "amount", defaultValue = "10") int amount) {
        List<TriviaQuestion> questions = triviaService.getVideoGameTrivia(amount);
        return ResponseEntity.ok(questions);
    }
}
