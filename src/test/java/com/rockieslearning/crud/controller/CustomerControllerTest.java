package com.rockieslearning.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.*;
import com.rockieslearning.crud.entity.OrderStatusName;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.service.CartService;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.OrderService;
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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jul, 2021
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @MockBean
    private CartService cartService;

    @MockBean
    private OrderService orderService;


    UserDto mockUserDto = new UserDto(Long.valueOf(1), "0123123123", "vivu@gmail.com", "213123123");

    UserDetailDto mockUserDetailDto = new UserDetailDto(1, "first", "last", "0123123123", "123", Long.valueOf(1));

    List<UserDetailDto> mockUserDetailDtos = new ArrayList<>();

    CartDto mockCartDto = new CartDto(1, Long.valueOf(1));

    CartFoodDto mockCartFoodDto = new CartFoodDto(1, 10, 20000.0, "name", "image");



    OrderDto mockOrderDto = new OrderDto(Long.valueOf(1), OrderStatusName.ORDERED,1);

    OrderFoodDto mockOrderFoodDto = new OrderFoodDto(1, 10, 20000.0, "name", "image");




    private static ObjectMapper mapper = new ObjectMapper();



    @Test
    public void testGetUserById() throws Exception {

        Mockito.when(userService.getUserById(ArgumentMatchers.anyLong())).thenReturn(mockUserDto);


        mockMvc.perform(get("/api/customers").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.phone", Matchers.equalTo("0123123123")))
                .andExpect(jsonPath("$.email", Matchers.equalTo("vivu@gmail.com")))
                .andExpect(jsonPath("$.password", Matchers.equalTo("213123123")));


    }


    @Test
    public void testAddUserDetailById() throws Exception {

        mockUserDetailDtos.add(mockUserDetailDto);
        Mockito.when(userService.saveUserDetail(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(mockUserDetailDto);


        String json = mapper.writeValueAsString(mockUserDetailDto);

        mockMvc.perform(post("/api/customers/details").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("first")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("last")))
                .andExpect(jsonPath("$.phone", Matchers.equalTo("0123123123")))
                .andExpect(jsonPath("$.address", Matchers.equalTo("123")))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }


    @Test
    public void testUpdateUserDetailById() throws Exception {

        mockUserDetailDtos.add(mockUserDetailDto);
        Mockito.when(userService.updateUserDetail(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(mockUserDetailDto);


        String json = mapper.writeValueAsString(mockUserDetailDto);

        mockMvc.perform(put("/api/customers/details/{detailId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("first")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("last")))
                .andExpect(jsonPath("$.phone", Matchers.equalTo("0123123123")))
                .andExpect(jsonPath("$.address", Matchers.equalTo("123")))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }


    @Test
    public void testDeleteUserDetailById() throws Exception {

        mockUserDetailDtos.add(mockUserDetailDto);
        Mockito.when(userService.deleteUserDetail(ArgumentMatchers.anyInt())).thenReturn("deleted");


        String json = mapper.writeValueAsString(mockUserDetailDto);

        mockMvc.perform(delete("/api/customers/details/{detailId}", 1).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();

    }





    @Test
    public void testGetCartByUserId() throws Exception {
        List<CartFoodDto> cartFoodDtoSet = new ArrayList<>();

        cartFoodDtoSet.add(mockCartFoodDto);

        mockCartDto.setCartFoods(cartFoodDtoSet);


        Mockito.when(cartService.getCartByUserId(ArgumentMatchers.anyLong())).thenReturn(mockCartDto);


        mockMvc.perform(get("/api/customers/cart").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }




    @Test
    public void testAddCartQty() throws Exception {
        List<CartFoodDto> cartFoodDtoSet = new ArrayList<>();

        cartFoodDtoSet.add(mockCartFoodDto);

        mockCartDto.setCartFoods(cartFoodDtoSet);


        CartFoodDto cartFoodDto = new CartFoodDto();
        cartFoodDto.setId(1);
        cartFoodDto.setAmount(12);

        String json = mapper.writeValueAsString(cartFoodDto);  //rq

        //rp
        Mockito.when(cartService.updateCart(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(mockCartDto);


        mockMvc.perform(post("/api/customers/cart").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }







    @Test
    public void testGetOrderByUserId() throws Exception {


        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();

        orderFoodDtoSet.add(mockOrderFoodDto);

        mockOrderDto.setOrderFoods(orderFoodDtoSet);

        List<OrderDto> orderDtos = new ArrayList<>();

        orderDtos.add(mockOrderDto);


        Mockito.when(orderService.getListOrderByUserId(ArgumentMatchers.anyLong())).thenReturn(orderDtos);


        mockMvc.perform(get("/api/customers/orders").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].status", Matchers.equalTo("ORDERED")))
                .andExpect(jsonPath("$[0].orderId", Matchers.equalTo(1)));

    }





    @Test
    public void testAddOrder() throws Exception {
        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();

        orderFoodDtoSet.add(mockOrderFoodDto);

        mockOrderDto.setOrderFoods(orderFoodDtoSet);



        //rp
        Mockito.when(orderService.createNewOrderFromCart(ArgumentMatchers.anyLong())).thenReturn(mockOrderDto);


        mockMvc.perform(post("/api/customers/orders?all=true").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                 /*them*/       .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }

}
