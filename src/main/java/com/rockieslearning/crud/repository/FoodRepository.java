package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByCategory(Category category);
}
