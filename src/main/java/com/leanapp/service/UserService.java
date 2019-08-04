package com.leanapp.service;

import com.leanapp.domain.User;
import com.leanapp.domain.exceptions.UserNotFoundException;
import com.leanapp.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
