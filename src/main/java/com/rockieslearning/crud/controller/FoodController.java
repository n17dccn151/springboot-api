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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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



    @GetMapping("")
    public ResponseEntity<List<FoodDto>> getAllFood(){

        List<FoodDto> foodDtos  = foodService.retrieveFoods();
        return new ResponseEntity<>(foodDtos, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDto> getFoodById(HttpServletRequest request,
                                                    @PathVariable("foodId") Integer foodId){

        FoodDto foodDto = foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodDto,HttpStatus.OK);
    }

    @GetMapping("/{foodId}/images")
    public ResponseEntity<List<FoodImageDto>> getFoodImageById(HttpServletRequest request,
                                               @PathVariable("foodId") Integer foodId){

        List<FoodImageDto> foodImageDtos  = foodService.getFoodImageByFoodId(foodId);
        return new ResponseEntity<>(foodImageDtos,HttpStatus.OK);
    }


    @PutMapping("/images/{imageId}")
    public ResponseEntity<Map<String, Boolean>> updateFoodImageById(HttpServletRequest request,
                                                                  @RequestBody FoodImageDto  foodImageDto,
                                                                  @PathVariable("imageId") Integer imageId){

        foodService.updateImage(imageId, foodImageDto);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }



    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Map<String, Boolean>> deleteFoodImageById(HttpServletRequest request,
                                                                  @PathVariable("imageId") Integer imageId){


        foodService.deleteImage(imageId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



    @PostMapping("/{foodId}/images")
    public ResponseEntity<List<FoodImageDto>> updateFoodImageById(HttpServletRequest request,
                                                                  @PathVariable("foodId") Integer foodId,
                                                                  @RequestBody FoodImageDto  foodImageDto){

        List<FoodImageDto> foodImageDtos  =new ArrayList<>();
        foodService.createImage(foodId, foodImageDto);

        return new ResponseEntity<>(foodImageDtos,HttpStatus.OK);
    }







    @PostMapping("")
    public ResponseEntity<FoodDto> addFood(HttpServletRequest request, @RequestBody FoodDto foodDto) throws ParseException {

        FoodDto saveFood = foodService.saveFood(foodDto);
        return new ResponseEntity<>(saveFood, HttpStatus.CREATED);
    }




    @PutMapping("/{foodId}")
    public ResponseEntity<Map<String, Boolean>> updateFood(HttpServletRequest request,
                                                               @PathVariable("foodId") Integer foodId,
                                                               @RequestBody FoodDto foodDto){


        foodService.updateFood(foodId, foodDto);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{foodId}")
    public ResponseEntity<Map<String, Boolean>> deleteFood(HttpServletRequest request,
                                                               @PathVariable("foodId") Integer foodId) throws ParseException {



        foodService.deleteFood(foodId);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
