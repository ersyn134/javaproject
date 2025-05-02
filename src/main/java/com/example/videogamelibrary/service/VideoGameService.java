// package com.example.videogamelibrary.service;

// import org.springframework.stereotype.Service;
// import com.example.videogamelibrary.entity.VideoGame;
// import com.example.videogamelibrary.repository.VideoGameRepository;
// import com.example.videogamelibrary.exception.ResourceNotFoundException;
// import java.util.List;

// @Service
// public class VideoGameService {
    
//     private final VideoGameRepository videoGameRepository;
    
//     public VideoGameService(VideoGameRepository videoGameRepository) {
//         this.videoGameRepository = videoGameRepository;
//     }
    
//     public VideoGame createVideoGame(VideoGame videoGame) {
//         return videoGameRepository.save(videoGame);
//     }
    
//     public VideoGame getVideoGameById(Long id) {
//         return videoGameRepository.findById(id)
//           .orElseThrow(() -> new ResourceNotFoundException("VideoGame not found with id " + id));
//     }
    
//     public List<VideoGame> getAllVideoGames() {
//         return videoGameRepository.findAll();
//     }
    
//     public VideoGame updateVideoGame(Long id, VideoGame videoGameDetails) {
//         VideoGame videoGame = getVideoGameById(id);
//         videoGame.setTitle(videoGameDetails.getTitle());
//         videoGame.setGenre(videoGameDetails.getGenre());
//         videoGame.setReleaseDate(videoGameDetails.getReleaseDate());
//         videoGame.setRating(videoGameDetails.getRating());
//         return videoGameRepository.save(videoGame);
//     }
//     public void deleteVideoGame(Long id) {
//         VideoGame videoGame = videoGameRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("VideoGame not found"));
    
//         // Очистка связанных LibraryItem перед удалением
//         videoGame.getLibraryItems().clear();
//         videoGameRepository.save(videoGame);  // Сохранение после очистки
    
//         videoGameRepository.delete(videoGame);
//     }
    
    
// }
package com.example.videogamelibrary.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.videogamelibrary.entity.VideoGame;
import com.example.videogamelibrary.repository.VideoGameRepository;
import com.example.videogamelibrary.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class VideoGameService {
    
    private final VideoGameRepository videoGameRepository;
    
    public VideoGameService(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    
    public VideoGame createVideoGame(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }
    @Cacheable(value = "videogames", key = "#id")
    public VideoGame getVideoGameById(Long id) {
        System.out.println("Loading from DB: video game " + id);
        return videoGameRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("VideoGame not found with id " + id));
    }
    
    
    public List<VideoGame> getAllVideoGames() {
        return videoGameRepository.findAll();
    }
    
    public VideoGame updateVideoGame(Long id, VideoGame videoGameDetails) {
        VideoGame videoGame = getVideoGameById(id);
        videoGame.setTitle(videoGameDetails.getTitle());
        videoGame.setGenre(videoGameDetails.getGenre());
        videoGame.setReleaseDate(videoGameDetails.getReleaseDate());
        videoGame.setRating(videoGameDetails.getRating());
        return videoGameRepository.save(videoGame);
    }
    
    public void deleteVideoGame(Long id) {
        VideoGame videoGame = videoGameRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("VideoGame not found"));
    
        // Очистка связанных LibraryItem перед удалением
        videoGame.getLibraryItems().clear();
        videoGameRepository.save(videoGame);  // Сохранение после очистки
    
        videoGameRepository.delete(videoGame);
    }
    
    // Новый метод для поиска видеоигр по title и genre
    public List<VideoGame> searchVideoGames(String query) {
        // Простейшая валидация: запрос должен содержать минимум 3 символа
        if (query == null || query.trim().length() < 3) {
            throw new IllegalArgumentException("Query must be at least 3 characters long.");
        }
        return videoGameRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(query, query);
    }
}
