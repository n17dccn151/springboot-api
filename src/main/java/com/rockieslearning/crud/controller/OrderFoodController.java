package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.OrderFood;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.OrderFoodService;
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
@RequestMapping("/api/orderfoods")
public class OrderFoodController {
    @Autowired
    private OrderFoodService orderFoodService;


    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public ResponseEntity<List<OrderFood>> getAllOrderFood(){

        List<OrderFood> orderFoods = new ArrayList<>();
        orderFoods = orderFoodService.retrieveOrderFoods();
        return new ResponseEntity<>(orderFoods, HttpStatus.OK);
    }

    @GetMapping("/{orderFoodId}")
    public ResponseEntity<OrderFood> getOrderFoodById(HttpServletRequest request,
                                            @PathVariable("orderFoodId") Integer orderFoodId){


        OrderFood orderFood = orderFoodService.getOrderFoodById(orderFoodId);
        if(orderFood==null){
            throw new FaResourceNotFoundException("OrderFood not found");
        }

        return new ResponseEntity<>(orderFood,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<OrderFood> addOrderFood(HttpServletRequest request, @RequestBody Map<String, Object> foodMap){



        Double price = Double.parseDouble(foodMap.get("price").toString());
        Integer amount = Integer.parseInt(foodMap.get("amount").toString());

        Integer orderId = Integer.parseInt(foodMap.get("order_id").toString());
        Integer foodid = Integer.parseInt(foodMap.get("food_id").toString());


        Order order = orderService.getOrderById(orderId);

        if(order==null){
            throw new FaResourceNotFoundException("order not found");
        }

        OrderFood orderFood = new OrderFood(price,amount, order);

        orderFoodService.saveOrderFood(orderFood);
        return new ResponseEntity<>(orderFood, HttpStatus.CREATED);
    }




    @PutMapping("/{orderFoodId}")
    public ResponseEntity<Map<String, Boolean>> updateOrderFood(HttpServletRequest request,
                                                           @PathVariable("orderFoodId") Integer orderFoodId,
                                                           @RequestBody Map<String, Object> foodMap){

        OrderFood existOrderFood = orderFoodService.getOrderFoodById(orderFoodId);
        if(existOrderFood==null){
            throw new FaResourceNotFoundException("OrderFood not found");
        }


        Double price = Double.parseDouble(foodMap.get("price").toString());
        Integer amount = Integer.parseInt( foodMap.get("amount").toString());


        existOrderFood.setPrice(price);
        existOrderFood.setAmount(amount);




        orderFoodService.saveOrderFood(existOrderFood);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{orderFoodId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderFood(HttpServletRequest request,
                                                           @PathVariable("orderFoodId") Integer orderFoodId){


        OrderFood orderFood = orderFoodService.getOrderFoodById(orderFoodId);
        if(orderFood==null){
            throw new FaResourceNotFoundException("orderFood not found");
        }


        orderFoodService.deleteOrderFood(orderFood);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
