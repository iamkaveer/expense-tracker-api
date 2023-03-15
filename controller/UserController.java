package com.expensetracker.MCT.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.MCT.Dto.UserDto;
import com.expensetracker.MCT.model.User;
import com.expensetracker.MCT.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
        // Check if user with the same email already exists
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

  

        // Save the user to the database
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the password matches the hashed password in the database
            if (userDto.getPassword().equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
