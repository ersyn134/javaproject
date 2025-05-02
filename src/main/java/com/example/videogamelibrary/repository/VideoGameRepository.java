package com.example.videogamelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.videogamelibrary.entity.VideoGame;
import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);
}
