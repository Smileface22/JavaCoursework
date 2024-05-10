package com.example.butcher_shop.services;

import com.example.butcher_shop.databases.User;
import com.example.butcher_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}