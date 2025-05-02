// package com.example.videogamelibrary.controller;

// import org.springframework.web.bind.annotation.*;
// import com.example.videogamelibrary.entity.VideoGame;
// import com.example.videogamelibrary.service.VideoGameService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
// import java.util.List;

// @RestController
// @RequestMapping("/api/videogames")
// public class VideoGameController {
    
//     private final VideoGameService videoGameService;
    
//     public VideoGameController(VideoGameService videoGameService) {
//         this.videoGameService = videoGameService;
//     }
    
//     @PostMapping
//     public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
//         VideoGame created = videoGameService.createVideoGame(videoGame);
//         return new ResponseEntity<>(created, HttpStatus.CREATED);
//     }
    
//     @GetMapping("/{id}")
//     public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id) {
//         VideoGame videoGame = videoGameService.getVideoGameById(id);
//         return ResponseEntity.ok(videoGame);
//     }
    
//     @GetMapping
//     public ResponseEntity<List<VideoGame>> getAllVideoGames() {
//         return ResponseEntity.ok(videoGameService.getAllVideoGames());
//     }
    
//     @PutMapping("/{id}")
//     public ResponseEntity<VideoGame> updateVideoGame(@PathVariable Long id, @RequestBody VideoGame videoGameDetails) {
//         VideoGame updated = videoGameService.updateVideoGame(id, videoGameDetails);
//         return ResponseEntity.ok(updated);
//     }
    
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteVideoGame(@PathVariable Long id) {
//         videoGameService.deleteVideoGame(id);
//         return ResponseEntity.noContent().build();
//     }
// }
package com.example.videogamelibrary.controller;

import org.springframework.web.bind.annotation.*;
import com.example.videogamelibrary.entity.VideoGame;
import com.example.videogamelibrary.service.VideoGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/videogames")
public class VideoGameController {
    
    private final VideoGameService videoGameService;
    
    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }
    
    @PostMapping
    public ResponseEntity<VideoGame> createVideoGame(@RequestBody VideoGame videoGame) {
        VideoGame created = videoGameService.createVideoGame(videoGame);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id) {
        VideoGame videoGame = videoGameService.getVideoGameById(id);
        return ResponseEntity.ok(videoGame);
    }
    
    @GetMapping
    public ResponseEntity<List<VideoGame>> getAllVideoGames() {
        return ResponseEntity.ok(videoGameService.getAllVideoGames());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateVideoGame(@PathVariable Long id, @RequestBody VideoGame videoGameDetails) {
        VideoGame updated = videoGameService.updateVideoGame(id, videoGameDetails);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoGame(@PathVariable Long id) {
        videoGameService.deleteVideoGame(id);
        return ResponseEntity.noContent().build();
    }
    
    // Новый endpoint для поиска видеоигр
    @GetMapping("/search")
    public ResponseEntity<List<VideoGame>> searchVideoGames(@RequestParam("query") String query) {
        try {
            List<VideoGame> result = videoGameService.searchVideoGames(query);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException ex) {
            // Возвращаем 400 Bad Request, если входной параметр некорректный
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
