package com.proiect.calorietracker;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepo;
    private final UserRepository userRepo;

    // Injectam ambele repository-uri
    public FoodService(FoodRepository foodRepo, UserRepository userRepo) {
        this.foodRepo = foodRepo;
        this.userRepo = userRepo;
    }

    // 1. Adaugam mancare pentru un anumit user
    public Food addFoodToUser(Long userId, Food food) {
        // Cautam userul. Daca nu exista, aruncam eroare
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Userul cu id " + userId + " nu a fost gasit!"));

        food.setUser(user); // Legam mancarea de user

        if (food.getDate() == null) {
            food.setDate(LocalDate.now()); // Punem data de azi daca lipseste
        }

        return foodRepo.save(food);
    }

    // 2. Vedem istoricul unui user
    public List<Food> getUserFoodHistory(Long userId) {
        return foodRepo.findByUserId(userId);
    }

    // 3. LOGICA COMPLEXA: Calculam progresul pe azi
    // Returnam un mesaj de tip String, ex: "Ai mancat 1500 / 2000 kcal. Mai ai 500."
    public String getDailyStats(Long userId) {
        int dailyLimit = 2000;

        // Luam doar mancarea de azi a userului
        List<Food> foodsToday = foodRepo.findByUserIdAndDate(userId, LocalDate.now());

        // Facem suma caloriilor
        int consumed = foodsToday.stream().mapToInt(Food::getCalories).sum();
        int remaining = dailyLimit - consumed;

        if (remaining >= 0) {
            return "Ai consumat " + consumed + " kcal. Mai ai " + remaining + " kcal pana la limita de " + dailyLimit + ".";
        } else {
            return "ATENTIE! Ai depasit limita cu " + Math.abs(remaining) + " kcal!";
        }
    }
}