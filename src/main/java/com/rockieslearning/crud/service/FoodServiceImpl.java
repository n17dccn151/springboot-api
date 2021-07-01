package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.mapper.CategoryMapper;
import com.rockieslearning.crud.mapper.FoodMapper;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.FoodRepository;
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
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository repository;

    @Autowired
    private FoodMapper mapper;


    public FoodServiceImpl(FoodRepository repository, FoodMapper modelMapper) {
        this.repository = repository;
        this.mapper = modelMapper;
    }

    @Override
    public FoodDto saveFood(FoodDto foodDto) throws FaBadRequestException, ParseException {
        Food food = mapper.toEntity(foodDto);
        return mapper.toDto(repository.save(food));
    }



    @Override
    public List<FoodDto> retrieveFoods() {
        List<Food> foods =  repository.findAll();

        return mapper.toListDto(foods);
    }

    @Override
    public FoodDto getFoodById(int id) throws FaResourceNotFoundException {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteFood(Integer foodId) throws ParseException {
        Food food = repository.findById(foodId).get();
        repository.delete(food);
    }

    @Override
    public void updateFood(Integer foodId, FoodDto foodDto) {
        Food existFood = repository.findById(foodId).get();



        existFood.setName(foodDto.getName());
        existFood.setPrice(foodDto.getPrice());
        existFood.setDescription(foodDto.getDescription());
        existFood.setRating(foodDto.getRating());

        repository.save(existFood);
    }





    


}
