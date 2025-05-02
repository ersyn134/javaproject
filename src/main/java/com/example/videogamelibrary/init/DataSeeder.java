// package com.example.videogamelibrary.init;

// import com.example.videogamelibrary.entity.VideoGame;
// import com.example.videogamelibrary.entity.Profile;
// import com.example.videogamelibrary.entity.LibraryItem;
// import com.example.videogamelibrary.entity.LibraryStatus;
// import com.example.videogamelibrary.repository.VideoGameRepository;
// import com.example.videogamelibrary.repository.ProfileRepository;
// import com.example.videogamelibrary.repository.LibraryItemRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import com.github.javafaker.Faker;
// import java.time.LocalDate;
// import java.util.Random;
// import java.util.List;
// import java.util.ArrayList;

// @Component
// public class DataSeeder implements CommandLineRunner {

//     private final VideoGameRepository videoGameRepository;
//     private final ProfileRepository profileRepository;
//     private final LibraryItemRepository libraryItemRepository;

//     public DataSeeder(VideoGameRepository videoGameRepository, 
//                       ProfileRepository profileRepository,
//                       LibraryItemRepository libraryItemRepository) {
//         this.videoGameRepository = videoGameRepository;
//         this.profileRepository = profileRepository;
//         this.libraryItemRepository = libraryItemRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         Faker faker = new Faker();
//         Random random = new Random();

//         // Генерация 20 фейковых видеоигр
//         List<VideoGame> videoGames = new ArrayList<>();
//         for (int i = 0; i < 20; i++) {
//             VideoGame game = VideoGame.builder()
//                 .title(faker.esports().game())
//                 .genre(faker.options().option("RPG", "FPS", "Adventure", "Strategy", "Simulation"))
//                 // Генерация даты релиза: год от 2000 до 2024, месяц и день случайные
//                 .releaseDate(LocalDate.of(random.nextInt(25) + 2000, random.nextInt(12) + 1, random.nextInt(28) + 1))
//                 // Рейтинг от 0 до 10 с округлением до одного знака после запятой
//                 .rating(Math.round(random.nextDouble() * 10 * 10.0) / 10.0)
//                 .build();
//             videoGames.add(game);
//         }
//         videoGameRepository.saveAll(videoGames);
//         System.out.println("20 фейковых видеоигр сохранены");

//         // Генерация 10 фейковых профилей
//         List<Profile> profiles = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             Profile profile = Profile.builder()
//                 .username(faker.name().username())
//                 .email(faker.internet().emailAddress())
//                 .build();
//             profiles.add(profile);
//         }
//         profileRepository.saveAll(profiles);
//         System.out.println("10 фейковых профилей сохранены");

//         // Генерация записей библиотеки (LibraryItem) для каждого профиля
//         // Каждый профиль получит от 1 до 3 случайных записей
//         for (Profile profile : profiles) {
//             int numItems = random.nextInt(3) + 1;
//             for (int i = 0; i < numItems; i++) {
//                 VideoGame randomGame = videoGames.get(random.nextInt(videoGames.size()));
//                 LibraryItem item = LibraryItem.builder()
//                     .profile(profile)
//                     .videoGame(randomGame)
//                     // Дата добавления случайно за последние 100 дней
//                     .dateAdded(LocalDate.now().minusDays(random.nextInt(100)))
//                     // Случайное количество часов игры от 0 до 500
//                     .playtimeHours(random.nextInt(501))
//                     .installed(random.nextBoolean())
//                     // Дата последнего запуска случайно за последние 50 дней
//                     .lastPlayed(LocalDate.now().minusDays(random.nextInt(50)))
//                     // Случайный статус из перечисления LibraryStatus
//                     .status(LibraryStatus.values()[random.nextInt(LibraryStatus.values().length)])
//                     .build();
//                 libraryItemRepository.save(item);
//             }
//         }
//         System.out.println("Фейковые записи библиотеки добавлены для каждого профиля");

//         System.out.println("Seeding complete!");
//     }
// }
