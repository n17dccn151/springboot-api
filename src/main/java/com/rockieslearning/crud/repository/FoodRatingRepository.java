package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.dto.FoodRatingDto;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.FoodRating;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodRatingRepository extends JpaRepository<FoodRating, Integer> {
    List<FoodRating> findAllByFood(Food food);
    Boolean existsByFoodAndUser(Food food, User user);
}
