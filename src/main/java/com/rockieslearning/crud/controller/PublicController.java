package com.rockieslearning.crud.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.View;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FoodService foodService;


//    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryDto>> getAllCategory() {
//
//        List<CategoryDto> categories = categoryService.retrieveCategories();
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }
//
//    @GetMapping("/categories/{categoryId}")
//    public ResponseEntity<CategoryDto> getCategoryById(HttpServletRequest request,
//                                                       @PathVariable("categoryId") Integer categoryId) {
//
//
//        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
//        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//    }

//    @JsonView(View.FoodWithImage.class)
//    @GetMapping("/foods")
//    public ResponseEntity<Map<String, Object>> getAllFood() {
//
//        List<FoodDto> foodDtos = foodService.retrieveFoods();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("foods", foodDtos);
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }
//
//    @JsonView(View.FoodWithImageComment.class)
//    @GetMapping("/foods/{foodId}")
//    public ResponseEntity<FoodDto> getFoodById(HttpServletRequest request,
//                                               @PathVariable("foodId") Integer foodId) {
//
//        FoodDto foodDto = foodService.getFoodById(foodId);
//
//
//        return new ResponseEntity<>(foodDto, HttpStatus.OK);
//    }


}