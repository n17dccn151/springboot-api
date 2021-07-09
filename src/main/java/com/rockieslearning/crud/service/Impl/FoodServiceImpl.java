package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.repository.FoodImageRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FoodImageRepository foodImageRepository;


    public FoodServiceImpl(FoodRepository repository, CategoryRepository categoryRepository, FoodImageRepository foodImageRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.foodImageRepository = foodImageRepository;
    }





    @Override
    public List<FoodDto> retrieveFoods() {
        List<Food> foods =  repository.findAll();
        return new FoodDto().toListDto(foods);
    }

    @Override
    public FoodDto getFoodById(int id) throws ResourceNotFoundException {
        return new FoodDto().toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + id)));

    }


    @Override
    public FoodDto saveFood(FoodDto foodDto) throws BadRequestException {
        Food food = new FoodDto().toEntity(foodDto);
        Category category =  categoryRepository.findById(foodDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " + foodDto.getCategoryId()));;
        ;
        food.setCategory(category);
        food.setFoodStatusName(FoodStatusName.AVAILABLE.toString());
        try {

            food = repository.save(food);
            List<FoodImage> images = new FoodImageDto().toListEntity(foodDto.getImages());
            Food finalFood = food;
            images.forEach(e->{
                e.setFood(finalFood);
                foodImageRepository.save(e);
            });


        } catch (Exception e) {
            throw new BadRequestException("invalid Request" + e.getMessage());
        }

        return new FoodDto().toDto(repository.findById(food.getFoodId())
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id")));

    }



    @Override
    public void deleteFood(Integer foodId) throws ResourceNotFoundException {



        Food food = repository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + foodId));;
        foodImageRepository.deleteAllByFood(food);
        repository.delete(food);
    }

    @Override
    public FoodDto updateFood(Integer foodId, FoodDto foodDto) throws BadRequestException {
        Food food = repository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("food not found for this id: " + foodDto.getFoodId()));;
        ;
        Category category =  categoryRepository.findById(foodDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category not found for this id: " + foodDto.getCategoryId()));;
        ;
        food.setCategory(category);
        food.setFoodId(foodId);

        for (Object s : FoodStatusName.values()) {
            if (foodDto.getStatus().equals(s.toString()) && foodDto.getStatus().equals(food.getFoodStatusName()) == false) {
                food.setFoodStatusName(foodDto.getStatus());
            }
        }


        foodImageRepository.deleteAllByFood(food);
        try {

            food = repository.save(food);
            List<FoodImage> images = new FoodImageDto().toListEntity(foodDto.getImages());
            Food finalFood = food;
            images.forEach(e->{
                e.setFood(finalFood);
                foodImageRepository.save(e);
            });


        } catch (Exception e) {
            throw new BadRequestException("invalid Request" +e.getMessage());
        }

        return new FoodDto().toDto(repository.getById(foodId));

    }

    @Override
    public List<FoodDto> getFoodByCategoryId(int id) throws ResourceNotFoundException{

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + id));
        List<Food> foods = repository.findByCategory(category);
        return new FoodDto().toListDto(foods);
    }


}
