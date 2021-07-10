package com.rockieslearning.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.service.FoodService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jun, 2021
 */

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;


    private static ObjectMapper mapper = new ObjectMapper();


    FoodDto mockFoodDto = new FoodDto(1, "name", 100000.0, 100, "AVAILABLE",  "description", 3);

    List<FoodDto> mockFoodDtos = new ArrayList<>();



    @Test
    public void testGetAllFood() throws Exception {

        mockFoodDtos.add(mockFoodDto);

        Mockito.when(foodService.retrieveFoods()).thenReturn(mockFoodDtos);


        mockMvc.perform(get("/api/foods").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$[0].description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$[0].price", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$[0].quantity", Matchers.equalTo(100)))
                .andExpect(jsonPath("$[0].status", Matchers.equalTo("AVAILABLE")))
                .andExpect(jsonPath("$[0].categoryId", Matchers.equalTo(3)));

    }




    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDto> getFoodById(HttpServletRequest request,
                                                    @PathVariable("foodId") Integer foodId){

        FoodDto foodDto = foodService.getFoodById(foodId);
        return new ResponseEntity<>(foodDto,HttpStatus.OK);
    }


    @Test
    public void testGetFoodById() throws Exception {



        Mockito.when(foodService.getFoodById(ArgumentMatchers.anyInt())).thenReturn(mockFoodDto);


        mockMvc.perform(get("/api/foods/{foodId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.price", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.quantity", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.status", Matchers.equalTo("AVAILABLE")))
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(3)));

    }





    @Test
    public void testAddFood() throws Exception {


        Mockito.when(foodService.saveFood(ArgumentMatchers.any())).thenReturn(mockFoodDto);


        String json = mapper.writeValueAsString(mockFoodDto);

        mockMvc.perform(post("/api/foods/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.price", Matchers.equalTo(11.1)))
                .andExpect(jsonPath("$.quantity", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.status", Matchers.equalTo("AVAILABLE")))
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(3)));

    }







    @Test
    public void testUpdateFood() throws Exception {

        Mockito.when(foodService.updateFood(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(mockFoodDto);


        String json = mapper.writeValueAsString(mockFoodDto);

        mockMvc.perform(put("/api/foods/{foodId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.foodId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.price", Matchers.equalTo(100000.0)))
                .andExpect(jsonPath("$.quantity", Matchers.equalTo(100)))
                .andExpect(jsonPath("$.status", Matchers.equalTo("AVAILABLE")))
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(3)));


    }


    @Test
    public void testDeleteFood() throws Exception {


        Mockito.when(foodService.deleteFood(ArgumentMatchers.anyInt())).thenReturn("deleted");


        MvcResult requestResult = mockMvc.perform(delete("/api/foods/{foodId}", 1)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();


    }



}
