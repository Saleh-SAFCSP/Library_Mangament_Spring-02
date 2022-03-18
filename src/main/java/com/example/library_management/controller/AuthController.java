package com.example.library_management.controller;


import com.example.library_management.model.ResponseAPI;
import com.example.library_management.model.User;
import com.example.library_management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body( authService.getUsers());
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody User user){
        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        authService.register(user);
        return ResponseEntity.status(201).body(new ResponseAPI("User Created ",201));
    }

}
