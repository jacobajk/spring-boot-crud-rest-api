package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll(); // Return all users from db
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "Saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setMajor(user.getMajor());
        return "Updated...";
    }

    @DeleteMapping
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "Delete user with the id: " + id;
    }


}
