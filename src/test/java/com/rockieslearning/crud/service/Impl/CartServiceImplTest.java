package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.CartFoodRepository;
import com.rockieslearning.crud.repository.CartRepository;
import com.rockieslearning.crud.repository.FoodRepository;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.CartService;
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

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by TanVOD on Jul, 2021
 */

@SpringBootTest
public class CartServiceImplTest {


    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    CartFoodRepository cartFoodRepository;

    @Mock
    FoodRepository foodRepository;


    @InjectMocks
    private CartServiceImpl cartService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();



    private Cart cart;
    private List<Cart> carts;
    private CartDto cartDto;
    private List<CartDto> cartDtos;
    private User user;
    private Set<CartFood> cartFoods;
    private CartFood cartFood;


    private Food food;
    private List<FoodImage> images;
    private List<FoodRating> ratings;
    private Category category;


    @BeforeEach
    public void setUp() {

        cartFoods = new HashSet<>();
        user = new User();
        cart = new Cart();
        carts = new ArrayList<>();

        user.setUserId(1L);
        cart.setCartId(1);
        cart.setUser(user);
        cart.setCartFoods(cartFoods);
        carts.add(cart);
        cartDto = new CartDto().toDto(cart);
        cartDtos = new CartDto().toListDto(carts);


        category = new Category(1, "name", "description", "image", null);
        images = new ArrayList<>();
        ratings = new ArrayList<>();

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


        cartFood = new CartFood();
        cartFood.setId(1);
        cartFood.setFood(food);
        cartFood.setCart(cart);
        cartFood.setAmount(11);


    }


    @AfterEach
    public void tearDown() {
        cart = null;
        carts = null;
        cartFood = null;
        cartDto = null;
        cartDtos =null;
        food = null;
        cartFood = null;
        cartFoods = null;
    }




    @Test
    public void shouldReturnAllCartDto() {


        when(cartRepository.findAll()).thenReturn(carts);

        List<CartDto> cartDtos1 = cartService.retrieveCarts();

//        assertEquals(categoryDtos1.size(),categoryDtos.size());

        assertThat(cartDtos).usingRecursiveComparison().isEqualTo(cartDtos1);

        verify(cartRepository, times(1)).findAll();
    }


    @Test
    public void whenGivenId_shouldReturnCart_ifFound() {
        when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.ofNullable(cart));

        CartDto cartDto1 = cartService.getCartById(cart.getCartId());

        assertThat(cartDto1).usingRecursiveComparison().isEqualTo(cartDto);
    }




    @Test()
    public void whenGivenId_shouldDeleteCart_ifFound() {

        when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));

        cartService.deleteCart(cart.getCartId());

        verify(cartRepository).delete(cart);

    }



    @Test()
    public void whenGivenId_shouldNotDeleteCart_ifNotFound() {

        given(cartRepository.findById(cart.getCartId())).willReturn(Optional.ofNullable(null));

        try {
            cartService.deleteCart(cart.getCartId());
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }


    }

    @Test
    public void whenGivenUserId_shouldReturnCart_ifFound() {
        when(cartRepository.findByUser(user)).thenReturn(cart);

        CartDto cartDto1 = cartService.getCartByUserId(user.getUserId());

        assertThat(cartDto1).usingRecursiveComparison().isEqualTo(cartDto);
    }

    @Test()
    public void whenGivenUserId_shouldNotReturnCart_ifNotFound() {

        given(cartRepository.findByUser(user)).willReturn(cart);

        try {
            cartService.getCartByUserId(user.getUserId());
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }


    }



    @Test
    public void whenGivenId_shouldUpdateCart_ifFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        when(cartRepository.findByUser(user)).thenReturn(cart);

        when(foodRepository.getById(anyInt())).thenReturn(food);

        when(cartFoodRepository.findCartFoodByFoodAndCart(food, cart)).thenReturn(cartFood);

        cartFoodRepository.save(cartFood);



        //verify(cartRepository, times(1)).save(any());

        verify(cartFoodRepository, times(1)).save(any());

    }



}
