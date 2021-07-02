package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.FoodImage;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.mapper.CategoryMapper;
import com.rockieslearning.crud.mapper.FoodImageMapper;
import com.rockieslearning.crud.mapper.FoodMapper;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.repository.FoodImageRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.service.FoodService;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FoodImageRepository foodImageRepository;

    @Autowired
    private FoodMapper mapper;

    @Autowired
    private FoodImageMapper foodImageMapper;


    public FoodServiceImpl(FoodRepository repository, CategoryRepository categoryRepository, FoodMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }



    @Override
    public FoodDto saveFood(FoodDto foodDto) throws BadRequestException, ParseException {
        Food food = mapper.toEntity(foodDto);

        int foodId = repository.save(food).getFoodId();
        return mapper.toDto(repository.getById(foodId));
    }



    @Override
    public List<FoodDto> retrieveFoods() {
        List<Food> foods =  repository.findAll();
        return mapper.toListDto(foods);
    }

    @Override
    public FoodDto getFoodById(int id) throws ResourceNotFoundException {
        return mapper.toDto(repository.findById(id).get());
    }



    @Override
    public void deleteFood(Integer foodId) throws ParseException {
        Food food = repository.findById(foodId).get();
        repository.delete(food);
    }

    @Override
    public void updateFood(Integer foodId, FoodDto foodDto) {


        Food food = mapper.toEntity(foodDto);
        food.setFoodId(foodId);
        repository.save(food);

    }

    @Override
    public List<FoodDto> getFoodByCategoryId(int id) {

        Category category = categoryRepository.getById(id);
        List<Food> foods = repository.findByCategory(category);
        return mapper.toListDto(foods);
    }

    @Override
    public List<FoodImageDto> getFoodImageByFoodId(Integer foodId) {
        Food food = repository.getById(foodId);
        List<FoodImage> images = foodImageRepository.getAllByFood(food);

        return foodImageMapper.toListDto(images);
    }

    @Override
    public void updateImage(Integer imageId, FoodImageDto foodImageDto) {
        FoodImage foodImage = foodImageRepository.getById(imageId);
        foodImage.setImage(foodImageDto.getUrl());
        foodImageRepository.save(foodImage);
    }

    @Override
    public FoodImageDto createImage(Integer foodId, FoodImageDto foodImageDto) {
        FoodImage foodImage = foodImageMapper.toEntity(foodImageDto);
        foodImageRepository.save(foodImage);

        return  foodImageMapper.toDto(foodImage);
    }

    @Override
    public void deleteImage(Integer imageId) {
        foodImageRepository.deleteById(imageId);
    }


}
