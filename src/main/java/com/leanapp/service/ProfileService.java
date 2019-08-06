package com.leanapp.service;

import com.leanapp.domain.Profile;
import com.leanapp.domain.exceptions.ProfileNotFoundException;
import com.leanapp.repository.ProfileRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveOrUpdate(Profile profile) {
        profileRepository.save(profile);
    }

    public Optional<Profile> getProfile(Long id) throws ProfileNotFoundException {
        return profileRepository.findById(id);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public void delete(Long id) {
        profileRepository.deleteById(id);
    }
}
