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

    public FoodServiceImpl(FoodRepository repository, CategoryRepository categoryRepository, FoodImageRepository foodImageRepository, FoodMapper mapper, FoodImageMapper foodImageMapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.foodImageRepository = foodImageRepository;
        this.mapper = mapper;
        this.foodImageMapper = foodImageMapper;
    }

    @Override
    public FoodDto saveFood(FoodDto foodDto) throws BadRequestException {
        Food food = mapper.toEntity(foodDto);

        int foodId;

        try {
             foodId = repository.save(food).getFoodId();
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");


        }
        return mapper.toDto(repository.getById(foodId));
    }



    @Override
    public List<FoodDto> retrieveFoods() {
        List<Food> foods =  repository.findAll();
        return mapper.toListDto(foods);
    }

    @Override
    public FoodDto getFoodById(int id) throws ResourceNotFoundException {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + id)));

    }



    @Override
    public void deleteFood(Integer foodId) throws ResourceNotFoundException {
        Food food = repository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + foodId));;

        repository.delete(food);
    }

    @Override
    public void updateFood(Integer foodId, FoodDto foodDto) throws BadRequestException {
        Food food = mapper.toEntity(foodDto);
        food.setFoodId(foodId);

        try {
            repository.save(food);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");


        }



    }

    @Override
    public List<FoodDto> getFoodByCategoryId(int id) throws ResourceNotFoundException{

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + id));
        List<Food> foods = repository.findByCategory(category);
        return mapper.toListDto(foods);
    }

    @Override
    public List<FoodImageDto> getFoodImageByFoodId(Integer foodId) throws ResourceNotFoundException{
        Food food = repository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id: " + foodId));;
        List<FoodImage> images = foodImageRepository.getAllByFood(food);

        return foodImageMapper.toListDto(images);
    }

    @Override
    public void updateImage(Integer imageId, FoodImageDto foodImageDto) throws ResourceNotFoundException{
        FoodImage foodImage = foodImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id: " + imageId));
        foodImage.setImage(foodImageDto.getUrl());

        try {
            foodImageRepository.save(foodImage);
        } catch (Exception e) {
            throw new BadRequestException("invalid Request");


        }
    }

    @Override
    public FoodImageDto createImage(Integer foodId, FoodImageDto foodImageDto) throws BadRequestException {
        FoodImage foodImage = foodImageMapper.toEntity(foodImageDto);

        try {
            foodImageRepository.save(foodImage);
        }
        catch (Exception e){
            throw  new BadRequestException("invalid Request");
        }


        return  foodImageMapper.toDto(foodImage);
    }

    @Override
    public void deleteImage(Integer imageId) throws ResourceNotFoundException{
        try {
            foodImageRepository.deleteById(imageId);
        }
        catch (Exception e){
            throw  new ResourceNotFoundException("Image not found");
        }

    }


}
