package com.example.library_management.service;


import com.example.library_management.model.User;
import com.example.library_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    public void register(User user) {
        userRepository.save(user);
    }
}
