package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.FoodService;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jun, 2021
 */

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;


    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public ResponseEntity<List<Food>> getAllFood(){

        List<Food> foods = new ArrayList<>();
        foods = foodService.retrieveFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<Food> getFoodById(HttpServletRequest request,
                                                    @PathVariable("foodId") Integer foodId){


        Food food = foodService.getFoodById(foodId);
        if(food==null){
            throw new FaResourceNotFoundException("Food not found");
        }

        return new ResponseEntity<>(food,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Food> addFood(HttpServletRequest request, @RequestBody Map<String, Object> foodMap){




        String name = (String) foodMap.get("name");
        Double price = Double.parseDouble(foodMap.get("price").toString());
        String description = (String) foodMap.get("description");
        Float rating = Float.parseFloat( foodMap.get("rating").toString());
        Integer categoryId = Integer.parseInt(foodMap.get("category_id").toString());


        Category category = categoryService.getCategoryById(categoryId);
        System.out.println(category.getCategoryId());
        if(category==null){
            throw new FaResourceNotFoundException("Category not found");
        }

        Food foodResult = new Food(name,price,description,rating,category);

        foodService.saveFood(foodResult);
        return new ResponseEntity<>(foodResult, HttpStatus.CREATED);
    }




    @PutMapping("/{foodId}")
    public ResponseEntity<Map<String, Boolean>> updateFood(HttpServletRequest request,
                                                               @PathVariable("foodId") Integer foodId,
                                                               @RequestBody Map<String, Object> foodMap){

        Food existFood = foodService.getFoodById(foodId);
        if(existFood==null){
            throw new FaResourceNotFoundException("Food not found");
        }

        String name = (String) foodMap.get("name");
        Double price = Double.parseDouble(foodMap.get("price").toString());
        String description = (String) foodMap.get("description");
        Float rating = Float.parseFloat( foodMap.get("rating").toString());


        existFood.setName(name);
        existFood.setPrice(price);
        existFood.setDescription(description);
        existFood.setRating(rating);




        foodService.saveFood(existFood);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{foodId}")
    public ResponseEntity<Map<String, Boolean>> deleteFood(HttpServletRequest request,
                                                               @PathVariable("foodId") Integer foodId){


        Food food = foodService.getFoodById(foodId);
        if(food==null){
            throw new FaResourceNotFoundException("Food not found");
        }


        foodService.deleteFood(food);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
