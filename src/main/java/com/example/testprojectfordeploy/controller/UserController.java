package com.example.testprojectfordeploy.controller;


import com.example.testprojectfordeploy.entity.User;
import com.example.testprojectfordeploy.exception.ResourcesNotFoundException;
import com.example.testprojectfordeploy.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllusers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("user not founded with id : " + userId));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") Long userId) {
        User userExisting = this.userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("user not founded with id : " + userId));
        userExisting.setFirstName(user.getFirstName());
        userExisting.setLastName(user.getLastName());
        userExisting.setEmail(user.getEmail());
        this.userRepository.save(userExisting);
        return userExisting;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId){
        User userExisting = this.userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("user not founded with id : " + userId));
        this.userRepository.delete(userExisting);
        return ResponseEntity.ok().build();
    }

}
