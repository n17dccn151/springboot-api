package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodService {

    public Food saveFood(Food food);

    public List<Food> retrieveFoods();

    public Food getFoodById(int id);

    public void deleteFood(Food food);




}
