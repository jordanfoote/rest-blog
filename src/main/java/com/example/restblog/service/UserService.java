package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersList() { // TODO: rename this 'getAllUsers'
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateEmail(Long userId, String newEmail){
        User user = getUserById(userId);
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}