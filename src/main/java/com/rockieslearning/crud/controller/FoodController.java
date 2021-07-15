package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.FoodService;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jun, 2021
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;


    @GetMapping("")
    public ResponseEntity<List<FoodDto>> getAllFood() {

        List<FoodDto> foodDtos = foodService.retrieveFoods();
        return new ResponseEntity<>(foodDtos, HttpStatus.OK);
    }


    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDto> getFoodById(HttpServletRequest request,
                                               @PathVariable("foodId") Integer foodId) {

        FoodDto foodDto = foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodDto, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<FoodDto> addFood(HttpServletRequest request,
                                           @RequestBody @Valid FoodDto foodDto) {

        FoodDto saveFood = foodService.saveFood(foodDto);
        return new ResponseEntity<>(saveFood, HttpStatus.CREATED);
    }


    @PutMapping("/{foodId}")
    public ResponseEntity<FoodDto> updateFood(HttpServletRequest request,
                                              @PathVariable("foodId") Integer foodId,
                                              @RequestBody @Valid FoodDto foodDto) {


        FoodDto updateFood = foodService.updateFood(foodId, foodDto);

        return new ResponseEntity<>(updateFood, HttpStatus.OK);
    }


    @DeleteMapping("/{foodId}")
    public ResponseEntity<Map<String, Boolean>> deleteFood(HttpServletRequest request,
                                                           @PathVariable("foodId") Integer foodId)  {


        foodService.deleteFood(foodId);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
