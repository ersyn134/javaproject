package com.example.videogamelibrary.controller;

import org.springframework.web.bind.annotation.*;
import com.example.videogamelibrary.entity.LibraryItem;
import com.example.videogamelibrary.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/library-items")
public class LibraryItemController {
    
    private final LibraryService libraryService;
    
    public LibraryItemController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    
    @PostMapping
    public ResponseEntity<LibraryItem> createLibraryItem(@RequestBody LibraryItem libraryItem) {
        LibraryItem created = libraryService.createLibraryItem(libraryItem);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LibraryItem> getLibraryItemById(@PathVariable Long id) {
        LibraryItem libraryItem = libraryService.getLibraryItemById(id);
        return ResponseEntity.ok(libraryItem);
    }
    
    @GetMapping
    public ResponseEntity<List<LibraryItem>> getAllLibraryItems() {
        return ResponseEntity.ok(libraryService.getAllLibraryItems());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LibraryItem> updateLibraryItem(@PathVariable Long id, @RequestBody LibraryItem libraryItemDetails) {
        LibraryItem updated = libraryService.updateLibraryItem(id, libraryItemDetails);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryItem(@PathVariable Long id) {
        libraryService.deleteLibraryItem(id);
        return ResponseEntity.noContent().build();
    }
}
