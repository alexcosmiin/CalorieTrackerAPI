package com.proiect.calorietracker;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface FoodRepository extends JpaRepository<Food, Long> {

    // Gaseste mancarea unui anumit user (dupa ID-ul userului)
    List<Food> findByUserId(Long userId);

    // Gaseste mancarea unui user, dar doar dintr-o anumita data (ex: azi)
    List<Food> findByUserIdAndDate(Long userId, LocalDate date);
}