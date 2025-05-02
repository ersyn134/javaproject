package com.example.videogamelibrary.service;

import com.example.videogamelibrary.dto.TriviaQuestion;
import com.example.videogamelibrary.dto.TriviaResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Service
public class TriviaService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    // URL внешнего API (Open Trivia API, категория Video Games: 15)
    private static final String TRIVIA_API_URL = "https://opentdb.com/api.php";

    public List<TriviaQuestion> getVideoGameTrivia(int amount) {
        URI uri = UriComponentsBuilder.fromHttpUrl(TRIVIA_API_URL)
                .queryParam("amount", amount)
                .queryParam("category", 15)
                .build()
                .toUri();
        
        TriviaResponse response = restTemplate.getForObject(uri, TriviaResponse.class);
        
        // Можно добавить дополнительную обработку/трансформацию данных
        // Например, фильтрация вопросов с определённой сложностью:
        // List<TriviaQuestion> filtered = response.getResults().stream()
        //     .filter(q -> "medium".equals(q.getDifficulty()))
        //     .collect(Collectors.toList());
        
        return response != null ? response.getResults() : List.of();
    }
}
