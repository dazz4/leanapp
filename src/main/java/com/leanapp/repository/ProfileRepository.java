package com.leanapp.repository;

import com.leanapp.domain.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Override
    List<Profile> findAll();
}
