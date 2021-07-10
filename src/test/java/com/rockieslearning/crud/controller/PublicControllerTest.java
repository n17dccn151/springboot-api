package com.rockieslearning.crud.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.View;
import com.rockieslearning.crud.service.CartService;
import com.rockieslearning.crud.service.CategoryService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private CategoryService categoryService;

    @MockBean
    private FoodService foodService;


    FoodDto mockFoodDto = new FoodDto(1, "name", 100000.0, 100, "AVAILABLE",  "description", 3);

    List<FoodDto> mockFoodDtos = new ArrayList<>();

    CategoryDto mockCategoryDto = new CategoryDto(1, "name", "description", "image url");

    List<CategoryDto> mockCategoryDtos = new ArrayList<>();





    @Test
    public void testGetAllCategory() throws Exception {

        mockCategoryDtos.add(mockCategoryDto);

        Mockito.when(categoryService.retrieveCategories()).thenReturn(mockCategoryDtos);


        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$[0].description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$[0].image", Matchers.equalTo("image url")));

    }


    @Test
    public void testGetCategoryById() throws Exception {

        Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyInt())).thenReturn(mockCategoryDto);


        mockMvc.perform(get("/api/categories/" + "1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("name")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("description")))
                .andExpect(jsonPath("$.image", Matchers.equalTo("image url")));


    }



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


}