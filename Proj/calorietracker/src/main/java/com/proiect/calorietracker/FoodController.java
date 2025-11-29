package com.proiect.calorietracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 1. ADD FOOD TO USER
    // Adauga o masa in jurnalul unui user specific (dupa ID)
    @PostMapping("/{userId}/food")
    public Food addFood(@PathVariable Long userId, @RequestBody Food food) {
        return foodService.addFoodToUser(userId, food);
    }

    // 2. VIEW HISTORY
    // Returneaza tot istoricul alimentar al unui user
    @GetMapping("/{userId}/food")
    public List<Food> getUserFood(@PathVariable Long userId) {
        return foodService.getUserFoodHistory(userId);
    }

    // 3. STATS (Complex Logic)
    // Calculeaza cate calorii mai are voie sa consume userul azi
    @GetMapping("/{userId}/stats")
    public String getUserStats(@PathVariable Long userId) {
        return foodService.getDailyStats(userId);
    }
}