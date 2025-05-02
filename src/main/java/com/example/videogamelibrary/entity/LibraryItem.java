package com.example.videogamelibrary.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "library_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Связь с пользователем (Profile)
    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    // Связь с игрой (VideoGame)
    @ManyToOne(optional = false)
    @JoinColumn(name = "video_game_id")
    private VideoGame videoGame;
    
    // Дата добавления игры в библиотеку
    private LocalDate dateAdded;
    
    // Количество часов, проведённых в игре
    private int playtimeHours;
    
    // Установлена ли игра
    private Boolean installed;
    
    // Дата последнего запуска
    private LocalDate lastPlayed;

    // Статус игры (например, OWNED, INSTALLED, PLAYING, UNINSTALLED)
    @Enumerated(EnumType.STRING)
    private LibraryStatus status;
}
