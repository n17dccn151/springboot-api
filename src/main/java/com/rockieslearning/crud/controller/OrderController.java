package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderRequestDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.service.FoodService;
import com.rockieslearning.crud.service.OrderService;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jun, 2021
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getAllOrder(){

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos = orderService.retrieveOrders();
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(HttpServletRequest request,
                                                    @PathVariable("orderId") Integer orderId){

        OrderDto orderDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<OrderDto> addOrder(HttpServletRequest request,
                                             @RequestBody OrderRequestDto orderRequestDto){



        OrderDto orderDto = orderService.createNewOrder(orderRequestDto);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderDto> addOrderFromCart(HttpServletRequest request,
                                                     @PathVariable("userId") Integer userId){

        OrderDto orderDto = orderService.createNewOrderFromCart(userId);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }






    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(HttpServletRequest request,
                                                               @PathVariable("OrderId") Integer orderId) throws ParseException {



        orderService.deleteOrder(orderId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
