package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodRatingDto;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
public interface RatingService {

    public FoodRatingDto saveRating(Long userId, FoodRatingDto foodRatingDto) throws BadRequestException;

    public List<FoodRatingDto> retrieveFoodRatings(Integer foodID);

    public void deleteFoodRating(Integer Id) throws ResourceNotFoundException;

    public FoodRatingDto updateFoodRating(Integer id, FoodRatingDto foodRatingDto) throws ResourceNotFoundException, BadRequestException;


}
