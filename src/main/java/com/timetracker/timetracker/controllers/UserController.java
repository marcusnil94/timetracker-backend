package com.timetracker.timetracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.User;
import com.timetracker.timetracker.services.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        
        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            
            return ResponseEntity.ok().body(existingUser.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to log in");
        }
    }
    
    
}
