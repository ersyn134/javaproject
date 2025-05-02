package com.example.videogamelibrary.service;

import org.springframework.stereotype.Service;
import com.example.videogamelibrary.entity.Profile;
import com.example.videogamelibrary.repository.ProfileRepository;
import com.example.videogamelibrary.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
    }
    
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
    
    public Profile updateProfile(Long id, Profile profileDetails) {
        Profile profile = getProfileById(id);
        profile.setUsername(profileDetails.getUsername());
        profile.setEmail(profileDetails.getEmail());
        return profileRepository.save(profile);
    }
    public void deleteProfile(Long id) {
        Profile profile = getProfileById(id);
        // Явно удаляем все связанные LibraryItem
        profile.getLibraryItems().clear();
        profileRepository.delete(profile);
    }
    
}
