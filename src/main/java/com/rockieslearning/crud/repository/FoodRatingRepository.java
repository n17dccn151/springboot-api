package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.FoodRating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodRatingRepository extends JpaRepository<FoodRating, Integer> {
}
