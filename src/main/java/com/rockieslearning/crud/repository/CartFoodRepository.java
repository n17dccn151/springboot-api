package com.rockieslearning.crud.repository;

import com.rockieslearning.crud.entity.CartFood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface CartFoodRepository extends JpaRepository<CartFood, Integer> {
}
