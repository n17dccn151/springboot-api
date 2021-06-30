package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
