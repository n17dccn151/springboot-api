package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodImageRepository extends JpaRepository<FoodImage, Integer> {
    List<FoodImage> getAllByFood(Food food);

}
