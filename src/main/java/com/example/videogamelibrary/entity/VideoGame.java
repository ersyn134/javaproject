package com.example.videogamelibrary.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
// import com.fasterxml.jackson.databind.annotation.JsonSerialize;
// import com.example.videogamelibrary.serialization.VideoGameDeserializer;
// import com.example.videogamelibrary.serialization.VideoGameSerializer;

@Entity
@Table(name = "video_games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @JsonSerialize(using = VideoGameSerializer.class)
// @JsonDeserialize(using = VideoGameDeserializer.class)
public class VideoGame {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private Double rating;
    
    // Обратная связь с LibraryItem (может вызывать циклическую сериализацию, поэтому игнорируем)
    @OneToMany(mappedBy = "videoGame", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonIgnore
    private Set<LibraryItem> libraryItems = new HashSet<>();
}
