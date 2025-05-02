package com.example.videogamelibrary.init;

import com.example.videogamelibrary.entity.VideoGame;
import com.example.videogamelibrary.entity.Profile;
import com.example.videogamelibrary.entity.LibraryItem;
import com.example.videogamelibrary.entity.LibraryStatus;
import com.example.videogamelibrary.repository.VideoGameRepository;
import com.example.videogamelibrary.repository.ProfileRepository;
import com.example.videogamelibrary.repository.LibraryItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VideoGameRepository videoGameRepository;
    private final ProfileRepository profileRepository;
    private final LibraryItemRepository libraryItemRepository;

    public DataInitializer(VideoGameRepository videoGameRepository,
                           ProfileRepository profileRepository,
                           LibraryItemRepository libraryItemRepository) {
        this.videoGameRepository = videoGameRepository;
        this.profileRepository = profileRepository;
        this.libraryItemRepository = libraryItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Очистка таблиц в порядке зависимостей
        libraryItemRepository.deleteAll();
        profileRepository.deleteAll();
        videoGameRepository.deleteAll();

        // Создаём видеоигры
        VideoGame game1 = VideoGame.builder()
                .title("The Witcher 3")
                .genre("RPG")
                .releaseDate(LocalDate.of(2015, 5, 19))
                .rating(9.5)
                .build();

        VideoGame game2 = VideoGame.builder()
                .title("Minecraft")
                .genre("Sandbox")
                .releaseDate(LocalDate.of(2011, 11, 18))
                .rating(9.0)
                .build();

        videoGameRepository.save(game1);
        videoGameRepository.save(game2);

        // Создаём профиль пользователя
        Profile profile = Profile.builder()
                .username("john_doe")
                .email("john@example.com")
                .build();

        profileRepository.save(profile);

        // Создаём записи библиотеки для пользователя
        LibraryItem item1 = LibraryItem.builder()
                .profile(profile)
                .videoGame(game1)
                .dateAdded(LocalDate.now())
                .playtimeHours(50)
                .installed(true)
                .lastPlayed(LocalDate.now().minusDays(1))
                .status(LibraryStatus.PLAYING)
                .build();

        LibraryItem item2 = LibraryItem.builder()
                .profile(profile)
                .videoGame(game2)
                .dateAdded(LocalDate.now())
                .playtimeHours(100)
                .installed(true)
                .lastPlayed(LocalDate.now().minusDays(3))
                .status(LibraryStatus.INSTALLED)
                .build();

        libraryItemRepository.save(item1);
        libraryItemRepository.save(item2);
    }
}
