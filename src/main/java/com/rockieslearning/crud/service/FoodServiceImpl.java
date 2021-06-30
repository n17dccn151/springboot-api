package com.rockieslearning.crud.service;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.exception.FaAuthException;
import com.rockieslearning.crud.exception.FaBadRequestException;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository repository;





    @Override
    public Food saveFood(Food food) throws FaBadRequestException {
        return repository.save(food);
    }



    @Override
    public List<Food> retrieveFoods() {
        return repository.findAll();
    }

    @Override
    public Food getFoodById(int id) throws FaResourceNotFoundException {
        return repository.findById(id).get();
    }

    @Override
    public void deleteFood(Food food) throws FaResourceNotFoundException {
        repository.delete(food);
    }





}
