package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodService {

    public FoodDto saveFood(FoodDto foodDto) throws ParseException;

    public List<FoodDto> retrieveFoods();

    public FoodDto getFoodById(int id) ;


    public void deleteFood(Integer foodId) throws ParseException;

    public void updateFood(Integer foodId, FoodDto foodDto);



}
