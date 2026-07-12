package com.example.login_signup.repository;

import com.example.login_signup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { 

    // Find by email method.
    User findByEmail(String email);

    // Method for checking whether an user exist by email.
    boolean existsByEmail(String email);
}
