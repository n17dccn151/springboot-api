package com.rockieslearning.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderFoodDto;
import com.rockieslearning.crud.entity.OrderStatusName;
import com.rockieslearning.crud.service.CartService;
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
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by TanVOD on Jun, 2021
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private OrderService orderService;



    OrderDto mockOrderDto = new OrderDto(Long.valueOf(1), OrderStatusName.ORDERED,1);

    OrderFoodDto mockOrderFoodDto = new OrderFoodDto(1, 10, 20000.0, "name", "image");


    private static ObjectMapper mapper = new ObjectMapper();




    @Test
    public void testGetAllOrder() throws Exception {
        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();

        orderFoodDtoSet.add(mockOrderFoodDto);

        mockOrderDto.setOrderFoods(orderFoodDtoSet);

        List<OrderDto> orderDtos = new ArrayList<>();

        orderDtos.add(mockOrderDto);

        Mockito.when(orderService.retrieveOrders()).thenReturn(orderDtos);


        mockMvc.perform(get("/api/orders").requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].userId", Matchers.equalTo(1)));

    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(HttpServletRequest request,
                                                 @PathVariable("orderId") Integer orderId) {

        OrderDto orderDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


    @Test
    public void testGetOrderById() throws Exception {
        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();

        orderFoodDtoSet.add(mockOrderFoodDto);

        mockOrderDto.setOrderFoods(orderFoodDtoSet);



        Mockito.when(orderService.getOrderById(ArgumentMatchers.anyInt())).thenReturn(mockOrderDto);


        mockMvc.perform(get("/api/orders/{orderId}", 1).requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }





    @Test
    public void testUpdateOrder() throws Exception {
        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();

        orderFoodDtoSet.add(mockOrderFoodDto);

        mockOrderDto.setOrderFoods(orderFoodDtoSet);


        String json = mapper.writeValueAsString(mockOrderDto);  //rq

        //rp
        Mockito.when(orderService.updateOrder(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(mockOrderDto);


        mockMvc.perform(put("/api/customers/orders/{orderId}", 1).requestAttr("userId", Long.valueOf(1)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.userId", Matchers.equalTo(1)));

    }



    @Test
    public void testDeleteOrder() throws Exception {


        Mockito.when(orderService.deleteOrder(ArgumentMatchers.anyInt())).thenReturn("deleted");


        MvcResult requestResult = mockMvc.perform(delete("/api/orders/{orderId}", 1)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)))
                .andReturn();


    }

}
