package com.example.springbootdemowebservices.controller;

import com.example.springbootdemowebservices.entity.User;
import com.example.springbootdemowebservices.exception.ResourceNotFoundException;
import com.example.springbootdemowebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(("User not found by id :" + userId)));
    }
    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
    // update user by id
    @PostMapping("/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable (value = "id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(("User not found by id :" + userId)));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }
    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable (value = "id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(("User not found by id :" + userId)));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
