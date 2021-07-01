package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.service.FoodService;
import com.rockieslearning.crud.service.OrderService;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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





    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getAllOrder(){

        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos = orderService.retrieveOrders();
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(HttpServletRequest request,
                                                    @PathVariable("orderId") Integer orderId){


        OrderDto orderDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

/*
    @PostMapping("")
    public ResponseEntity<Order> addOrder(HttpServletRequest request, @RequestBody Map<String, Object> orderMap){





        Integer userId = Integer.parseInt(orderMap.get("userId").toString());
        Double price = Double.parseDouble(orderMap.get("price").toString());
        Integer amount = Integer.parseInt(orderMap.get("amount").toString());
        String status = (String) orderMap.get("status");
        Integer foodId = Integer.parseInt(orderMap.get("foodId").toString());





        User user = userService.getUserById(userId);
        if(user==null){
            throw new FaResourceNotFoundException("user not found");
        }

        Food food = foodService.getFoodById(foodId);
        if(food==null){
            throw new FaResourceNotFoundException("food not found");
        }

        Order order = new Order();
        order.setAmount(amount);
        order.setPrice(price);
        order.setStatus(status);
        order.setUser(user);
        //

        OrderFood  orderFood = new OrderFood();
        orderFood.setFood(food);
        orderFood.setOrder(order);
//        orderFood


        Order orderResult =  orderService.saveOrder(order);
        return new ResponseEntity<>(orderResult, HttpStatus.CREATED);
    }


*/

//    @PutMapping("/{orderId}")
//    public ResponseEntity<Map<String, Boolean>> updateOrder(HttpServletRequest request,
//                                                               @PathVariable("orderId") Integer orderId,
//                                                               @RequestBody Order order){
//        Order existOrder = orderService.getOrderById(orderId);
//        if(existOrder==null){
//            throw new FaResourceNotFoundException("Order not found");
//        }
//        existOrder.setPrice(order.getPrice());
//        existOrder.setAmount(order.getAmount());
//        existOrder.setStatus(order.getStatus());
//        existOrder.setOrderFoods(order.getOrderFoods());
//
//
//        Map<String, Boolean> map = new HashMap<>();
//        map.put("success", true);
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }


    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(HttpServletRequest request,
                                                               @PathVariable("OrderId") Integer orderId) throws ParseException {



        orderService.deleteOrder(orderId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
