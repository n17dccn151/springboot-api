package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */
public interface FoodService {

    public FoodDto saveFood(FoodDto foodDto) throws BadRequestException;

    public List<FoodDto> retrieveFoods();

    public FoodDto getFoodById(int id) throws ResourceNotFoundException;


    public void deleteFood(Integer foodId) throws ResourceNotFoundException;

    public FoodDto updateFood(Integer foodId, FoodDto foodDto) throws ResourceNotFoundException, BadRequestException;


    public List<FoodDto> getFoodByCategoryId(int id) throws ResourceNotFoundException;



}
