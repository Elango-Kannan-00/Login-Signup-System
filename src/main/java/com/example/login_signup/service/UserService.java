package com.example.login_signup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login_signup.dto.UserDetailsDto;
import com.example.login_signup.dto.UserLoginDto;
import com.example.login_signup.dto.UserRegistrationDto;
import com.example.login_signup.entity.User;
import com.example.login_signup.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Signup user method.
    public String createUser(UserRegistrationDto dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            return "Email already exists";
        }

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        

        repository.save(user);

        return "User Created Successfully";

    }

    // Login user emthod.
    public String loginUser(UserLoginDto dto) {

        User user = repository.findByEmail(dto.getEmail());

        if (user == null) {
            return "No user found";
        }

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return "Login Successful!";
        }
        
        return "Login Unsuccessfull!";
    }

    // Get all users.
    public List<UserDetailsDto> getAllUsers() {
        List<User> users = repository.findAll();

        List<UserDetailsDto> response = new ArrayList<>();

        for (User user : users) {
            UserDetailsDto dto = new UserDetailsDto();

            dto.setUserName(user.getUserName());
            dto.setEmail(user.getEmail());

            response.add(dto);
        } 

        return response;
    }
}
