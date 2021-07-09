package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.FoodRatingDto;
import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.FoodRating;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jul, 2021
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private RatingService ratingService;





    @PostMapping("")
    public ResponseEntity<FoodRatingDto> addUser(HttpServletRequest request,
                                                 @RequestBody FoodRatingDto foodRatingDto) throws ParseException {

        Long userId = (Long) request.getAttribute("userId");
        FoodRatingDto dto =  ratingService.saveRating(userId, foodRatingDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }




    @PutMapping("/{ratingId}")
    public ResponseEntity<FoodRatingDto> updateUser(HttpServletRequest request,
                                              @PathVariable("ratingId") Integer ratingId,
                                              @RequestBody FoodRatingDto foodRatingDto){

        FoodRatingDto dto =  ratingService.updateFoodRating(ratingId, foodRatingDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Integer ratingId) throws ParseException {

        ratingService.deleteFoodRating(ratingId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
