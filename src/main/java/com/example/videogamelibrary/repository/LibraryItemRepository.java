package com.example.videogamelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.videogamelibrary.entity.LibraryItem;

public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {
}
