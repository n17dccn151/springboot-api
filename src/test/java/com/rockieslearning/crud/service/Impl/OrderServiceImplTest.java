package com.rockieslearning.crud.service.Impl;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.repository.*;
import com.rockieslearning.crud.service.OrderService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@SpringBootTest
public class OrderServiceImplTest {


    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FoodRepository foodRepository;

    @Mock
    OrderFoodRepository orderFoodRepository;

    @Mock
    CartRepository cartRepository;


    @InjectMocks
    private OrderServiceImpl orderService;

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
    private FoodImage image;
    private List<FoodImage> images;
    private List<FoodRating> ratings;
    private Category category;

    private Order order;
    private List<Order> orders;
    private OrderDto orderDto;
    private List<OrderDto> orderDtos;

    private OrderFood orderFood;
    private Set<OrderFood> orderFoods;

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
        image = new FoodImage();
        image.setId(1);
        image.setImage("iamge");
        images.add(image);

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


        orderFood = new OrderFood();
        orderFood.setPrice(100000.0);
        orderFood.setFood(food);
        orderFood.setAmount(100);
        orderFood.setId(1);

        orderFoods = new HashSet<>();
        orderFoods.add(orderFood);

        order = new Order();
        order.setOrderFoods(orderFoods);
        order.setUser(user);
        order.setStatus("ORDERED");
        orders = new ArrayList<>();
        orders.add(order);

        orderDtos = new OrderDto().toListDto(orders);
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
    public void shouldReturnAllOrderDto() {


        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> orderDtos1 = orderService.retrieveOrders();

        assertThat(orderDtos1).usingRecursiveComparison().isEqualTo(orderDtos);

        verify(orderRepository, times(1)).findAll();
    }




    @Test
    public void whenGivenId_shouldReturnOrder_ifFound() {
        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.ofNullable(order));

        OrderDto orderDto1 = orderService.getOrderById(order.getOrderId());

        assertThat(orderDto1).usingRecursiveComparison().isEqualTo(orderDto);
    }




    @Test()
    public void whenGivenId_shouldDeleteOrder_ifFound() {

        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

        orderService.deleteOrder(order.getOrderId());

        verify(cartRepository).delete(cart);

    }



    @Test()
    public void whenGivenId_shouldNotDeleteCart_ifNotFound() {

        given(orderRepository.findById(order.getOrderId())).willReturn(Optional.ofNullable(null));

        try {
            orderService.deleteOrder(order.getOrderId());
        } catch (ResourceNotFoundException e) {
            exception.expect(ResourceNotFoundException.class);
        }


    }



    @Test
    public void whenGivenUserId_shouldReturnListOrder_ifFound() {

        when(userRepository.getById(user.getUserId())).thenReturn(user);

        when(orderRepository.findByUser(user)).thenReturn(orders);

        List<OrderDto> orderDtos1 = orderService.getListOrderByUserId(user.getUserId());

        assertThat(orderDtos1).usingRecursiveComparison().isEqualTo(orderDtos);
    }



    @Test
    public void whenGivenId_shouldUpdateOrder_ifFound() {

        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.ofNullable(order));

        when(foodRepository.getById(anyInt())).thenReturn(food);


        foodRepository.save(food);

        orderRepository.save(order);

        verify(foodRepository, times(1)).save(any());

        verify(orderRepository, times(1)).save(any());

    }




}
