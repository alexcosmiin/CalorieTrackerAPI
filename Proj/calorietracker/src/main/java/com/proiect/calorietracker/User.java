package com.proiect.calorietracker;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // Un user are mai multe mese
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Food> foods;
}