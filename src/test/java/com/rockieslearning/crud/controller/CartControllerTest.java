package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.service.CartService;
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
import java.text.ParseException;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jul, 2021
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CartService cartService;


    CartDto mockCartDto = new CartDto(1, Long.valueOf(1));

    CartFoodDto mockCartFoodDto = new CartFoodDto(1, 10, 20000.0, "name", "image");





    @Test
    public void testGetAllCart() throws Exception {
        List<CartFoodDto> cartFoodDtoSet = new ArrayList<>();

        cartFoodDtoSet.add(mockCartFoodDto);

        mockCartDto.setCartFoods(cartFoodDtoSet);

        List<CartDto> cartDtos = new ArrayList<>();

        cartDtos.add(mockCartDto);

        Mockito.when(cartService.retrieveCarts()).thenReturn(cartDtos);


        mockMvc.perform(get("/api/cart").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].userId", Matchers.equalTo(1)));

    }





    @Test
    public void testDeleteCart() throws Exception {


        Mockito.when(cartService.deleteCart(ArgumentMatchers.anyInt())).thenReturn("deleted");


        MvcResult requestResult = mockMvc.perform(delete("/api/cart/{cartId}", 1)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();


    }



}
