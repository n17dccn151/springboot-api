package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<Order>> getAllOrder(){

        List<Order> orders = new ArrayList<>();
        orders = orderService.retrieveOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(HttpServletRequest request,
                                                    @PathVariable("orderId") Integer orderId){


        Order order = orderService.getOrderById(orderId);
        if(order==null){
            throw new FaResourceNotFoundException("Order not found");
        }
        return new ResponseEntity<>(order,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Order> addOrder(HttpServletRequest request, @RequestBody Order order){


        Order orderResult =  orderService.saveOrder(order);
        return new ResponseEntity<>(orderResult, HttpStatus.CREATED);
    }




    @PutMapping("/{orderId}")
    public ResponseEntity<Map<String, Boolean>> updateOrder(HttpServletRequest request,
                                                               @PathVariable("orderId") Integer orderId,
                                                               @RequestBody Order order){
        Order existOrder = orderService.getOrderById(orderId);
        if(existOrder==null){
            throw new FaResourceNotFoundException("Order not found");
        }
        existOrder.setPrice(order.getPrice());
        existOrder.setAmount(order.getAmount());
        existOrder.setStatus(order.getStatus());
        existOrder.setOrderFoods(order.getOrderFoods());


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(HttpServletRequest request,
                                                               @PathVariable("OrderId") Integer orderId){


        Order order = orderService.getOrderById(orderId);
        if(order==null){
            throw new FaResourceNotFoundException("Order not found");
        }


        orderService.deleteOrder(order);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
