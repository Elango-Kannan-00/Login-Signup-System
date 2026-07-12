package com.example.login_signup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login_signup.dto.UserDetailsDto;
import com.example.login_signup.dto.UserLoginDto;
import com.example.login_signup.dto.UserRegistrationDto;
import com.example.login_signup.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class AuthController {
    
    @Autowired
    private UserService service;

    // HTTP POST
    @PostMapping("/register")
    public String createUser(@Valid @RequestBody UserRegistrationDto user) {
        return service.createUser(user);
    }

    // HTTP POST
    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody UserLoginDto user) {
        return service.loginUser(user);
    }
    
    // HTTP GET
    @GetMapping
    public List<UserDetailsDto> getAllUsers() {
        return service.getAllUsers();
    }
}
