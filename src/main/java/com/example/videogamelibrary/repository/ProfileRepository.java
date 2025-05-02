package com.example.videogamelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.videogamelibrary.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
