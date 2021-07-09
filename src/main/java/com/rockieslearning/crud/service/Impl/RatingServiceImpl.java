package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.dto.FoodRatingDto;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.FoodImage;
import com.rockieslearning.crud.entity.FoodRating;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.FoodRatingRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    FoodRepository  foodRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRatingRepository foodRatingRepository;

    @Override
    public FoodRatingDto saveRating(Long userId, FoodRatingDto foodRatingDto) throws BadRequestException {

        Food food = foodRepository.findById(foodRatingDto.getFoodId())
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id"));


        if(foodRatingRepository.existsByFoodAndUser(food, user)){
            throw new BadRequestException("Invalid Request");
        }

        FoodRating foodRating = new FoodRatingDto().toEntity(foodRatingDto);
        foodRating.setFood(food);
        foodRating.setUser(user);

        return new FoodRatingDto().toDto(foodRatingRepository.save(foodRating));
    }

    @Override
    public List<FoodRatingDto> retrieveFoodRatings(Integer foodID) {
        Food food = foodRepository.findById(foodID)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + foodID));

        List<FoodRating> foodRatings =  foodRatingRepository.findAllByFood(food);

        return new FoodRatingDto().toListDto(foodRatings);
    }

    @Override
    public void deleteFoodRating(Integer id) throws ResourceNotFoundException {

        FoodRating foodRating = foodRatingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("foodRating not found for this id: " + id));

        try {
            foodRatingRepository.delete(foodRating);

        } catch (Exception e) {
            throw new BadRequestException("invalid Request" + e.getMessage());
        }
    }

    @Override
    public FoodRatingDto updateFoodRating(Integer id, FoodRatingDto foodRatingDto) throws ResourceNotFoundException, BadRequestException {
        FoodRating foodRating = foodRatingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("foodRating not found for this id: " + id));
        foodRating.setRate(foodRatingDto.getRating());
        foodRating.setComment(foodRating.getComment());

        return new FoodRatingDto().toDto(foodRatingRepository.save(foodRating));
    }


}
