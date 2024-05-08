package com.example.butcher_shop.services;

import com.example.butcher_shop.databases.User;
import com.example.butcher_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    public boolean authenticateUser(String name, String password) {
        User user = userRepository.findByUsername(name);
        return user != null && user.getPassword().equals(password);
    }
}