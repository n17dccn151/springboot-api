package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.repository.FoodImageRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.service.FoodService;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Created by TanVOD on Jun, 2021
 */

@SpringBootTest
public class FoodServiceImplTest {



    @Mock
    private FoodRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FoodImageRepository foodImageRepository;

    @InjectMocks
    private FoodServiceImpl foodService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();



    private Food food;
    private List<Food> foods;
    private FoodDto foodDto;
    private List<FoodDto> foodDtos;

    private Category category;
    private List<FoodImage> images;
    private List<FoodRating> ratings;


    @BeforeEach
    public void setUp() {

        category = new Category(1, "name", "description", "image", null);
        images = new ArrayList<>();
        ratings = new ArrayList<>();


        foods = new ArrayList<>();
        food = new Food();
        food.setFoodId(1);
        food.setFoodStatusName("AVAILABLE");
        food.setQuantity(100);
        food.setImages(null);
        food.setPrice(100000D);
        food.setName("name");
        food.setCategory(category);
        food.setRatings(ratings);
        food.setImages(images);

        foods.add(food);

        foodDto = new FoodDto().toDto(food);
        foodDtos = new FoodDto().toListDto(foods);

    }


    @AfterEach
    public void tearDown() {
        food = null;
        foodDto = null;

        foods = null;
        foodDtos = null;
    }



    @Test
    public void whenGivenFoodDto_shouldAddFood() {

        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.ofNullable(category));

        when(repository.save(any(Food.class))).thenReturn(food);

        when(repository.findById(food.getFoodId())).thenReturn(Optional.ofNullable(food));

        foodService.saveFood(foodDto);

        verify(repository, times(1)).save(any());

    }



    @Test
    public void shouldReturnAllFoodDto() {


        when(repository.findAll()).thenReturn(foods);

        List<FoodDto> foodDtos1 = foodService.retrieveFoods();

//        assertEquals(categoryDtos1.size(),categoryDtos.size());

        assertThat(foodDtos).usingRecursiveComparison().isEqualTo(foodDtos1);

        verify(repository, times(1)).findAll();
    }



    @Test
    public void whenGivenId_shouldReturnFood_ifFound() {
        when(repository.findById(food.getFoodId())).thenReturn(Optional.ofNullable(food));

        FoodDto foodDto1 = foodService.getFoodById(food.getFoodId());

        assertThat(foodDto1).usingRecursiveComparison().isEqualTo(foodDto);
    }


    @Test()
    public void whenGivenId_shouldDeleteFood_ifFound() {

        when(repository.findById(food.getFoodId())).thenReturn(Optional.of(food));

        foodService.deleteFood(food.getFoodId());

        verify(repository).delete(food);

    }



    @Test()
    public void whenGivenId_shouldNotDeleteFood_ifNotFound() {

        given(repository.findById(food.getFoodId())).willReturn(Optional.ofNullable(null));

        try {
            foodService.deleteFood(food.getFoodId());
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }


    }

    @Test
    public void whenGivenId_shouldUpdateCategory_ifFound() {


        given(categoryRepository.findById(category.getCategoryId())).willReturn(Optional.ofNullable(category));

        when(repository.save(any(Food.class))).thenReturn(food);

        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(food));

        foodService.updateFood(food.getFoodId(), foodDto);

        verify(repository, times(1)).save(any());


    }


    @Test
    public void whenGivenId_shouldNotUpdateFood_ifNotFound() {

        given(repository.findById(food.getFoodId())).willReturn(Optional.ofNullable(null));

        try {
            foodService.updateFood(food.getFoodId(), foodDto);
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }
    }









}
