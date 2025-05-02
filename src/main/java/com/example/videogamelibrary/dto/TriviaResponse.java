package com.example.videogamelibrary.dto;

import lombok.Data;
import java.util.List;

@Data
public class TriviaResponse {
    private int response_code;
    private List<TriviaQuestion> results;
}
