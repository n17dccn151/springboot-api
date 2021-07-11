package com.rockieslearning.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.FoodRatingDto;
import com.rockieslearning.crud.service.FoodService;
import com.rockieslearning.crud.service.RatingService;
import com.rockieslearning.crud.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jul, 2021
 */


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;


    private static ObjectMapper mapper = new ObjectMapper();

    FoodRatingDto mockFoodRatingDto = new FoodRatingDto(1,1,Long.valueOf(1), "comment", 5, "user name");




    @Test
    public void testAddRating() throws Exception {


        Mockito.when(ratingService.saveRating(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(mockFoodRatingDto);


        String json = mapper.writeValueAsString(mockFoodRatingDto);

        mockMvc.perform(post("/api/comments/").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.comment", Matchers.equalTo("comment")))
                .andExpect(jsonPath("$.rating", Matchers.equalTo(5)))
                .andExpect(jsonPath("$.userName", Matchers.equalTo("user name")));


    }



    @PutMapping("/{ratingId}")
    public ResponseEntity<FoodRatingDto> updateUser(HttpServletRequest request,
                                                    @PathVariable("ratingId") Integer ratingId,
                                                    @RequestBody @Valid FoodRatingDto foodRatingDto) {

        FoodRatingDto dto = ratingService.updateFoodRating(ratingId, foodRatingDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @Test
    public void testUpdateRating() throws Exception {


        Mockito.when(ratingService.updateFoodRating(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(mockFoodRatingDto);


        String json = mapper.writeValueAsString(mockFoodRatingDto);

        mockMvc.perform(put("/api/comments/{ratingId}", 1).requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.comment", Matchers.equalTo("comment")))
                .andExpect(jsonPath("$.rating", Matchers.equalTo(5)))
                .andExpect(jsonPath("$.userName", Matchers.equalTo("user name")));


    }




    @Test
    public void testDeleteCart() throws Exception {


        Mockito.when(ratingService.deleteFoodRating(ArgumentMatchers.anyInt())).thenReturn("deleted");


        MvcResult requestResult = mockMvc.perform(delete("/api/comments/{ratingId}", 1)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();
    }




}
