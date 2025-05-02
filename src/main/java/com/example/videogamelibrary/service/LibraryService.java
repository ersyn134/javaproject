package com.example.videogamelibrary.service;

import org.springframework.stereotype.Service;
import com.example.videogamelibrary.entity.LibraryItem;
import com.example.videogamelibrary.repository.LibraryItemRepository;
import com.example.videogamelibrary.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class LibraryService {
    
    private final LibraryItemRepository libraryItemRepository;
    
    public LibraryService(LibraryItemRepository libraryItemRepository) {
        this.libraryItemRepository = libraryItemRepository;
    }
    
    public LibraryItem createLibraryItem(LibraryItem libraryItem) {
        return libraryItemRepository.save(libraryItem);
    }
    
    public LibraryItem getLibraryItemById(Long id) {
        return libraryItemRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Library item not found with id " + id));
    }
    
    public List<LibraryItem> getAllLibraryItems() {
        return libraryItemRepository.findAll();
    }
    
    public LibraryItem updateLibraryItem(Long id, LibraryItem libraryItemDetails) {
        LibraryItem libraryItem = getLibraryItemById(id);
        
        // Обновляем данные видеоигры
        libraryItem.setVideoGame(libraryItemDetails.getVideoGame());
        
        // Обновляем профиль (пользователя)
        libraryItem.setProfile(libraryItemDetails.getProfile());
        
        // Обновляем статус (например, PLAYING, INSTALLED, OWNED, UNINSTALLED)
        libraryItem.setStatus(libraryItemDetails.getStatus());
        
        // Обновляем дополнительные данные
        libraryItem.setPlaytimeHours(libraryItemDetails.getPlaytimeHours());
        libraryItem.setInstalled(libraryItemDetails.getInstalled());
        libraryItem.setLastPlayed(libraryItemDetails.getLastPlayed());
        
        return libraryItemRepository.save(libraryItem);
    }
    
    public void deleteLibraryItem(Long id) {
        LibraryItem libraryItem = getLibraryItemById(id);
        libraryItemRepository.delete(libraryItem);
    }
}
