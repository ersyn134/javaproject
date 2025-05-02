package com.example.videogamelibrary.exception;

public class VideoGameNotFoundException extends RuntimeException {
    public VideoGameNotFoundException(String message) {
        super(message);
    }
}

