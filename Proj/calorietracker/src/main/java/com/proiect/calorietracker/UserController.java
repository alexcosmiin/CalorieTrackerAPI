package com.proiect.calorietracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // 1. CREATE USER
    // Endpoint pentru a inregistra un utilizator nou in aplicatie
    @PostMapping
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    // 2. LIST USERS
    // Afiseaza toti utilizatorii (util pentru verificare rapida)
    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}