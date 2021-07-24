package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.ResponseList;
import com.rockieslearning.crud.helper.SortDirection;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<List<FoodDto>> getAllFood(


            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "foodId,desc") String[] sort


    ) {

        SortDirection sortDirection = new SortDirection();
        List<Sort.Order> orders;
        orders = sortDirection.getSortOrders(sort);
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));


        List<FoodDto> foodDtos = new ArrayList<>();

        if (name == null) {
            foodDtos = foodService.retrieveFoods(pagingSort);
        } else {
            foodDtos = foodService.getFoodByName(name, pagingSort);
        }


        ResponseList responseList = new ResponseList();
        responseList.setTotalItems(foodService.retrieveFoods().size());
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
                                                           @PathVariable("foodId") Integer foodId) {


        foodService.deleteFood(foodId);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
