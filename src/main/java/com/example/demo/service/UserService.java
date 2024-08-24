package com.example.demo.service;

import com.example.demo.handler.error.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private boolean userExists(String id) {
        return !userRepository.existsById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        if (userExists(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
         //user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        if (userExists(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}