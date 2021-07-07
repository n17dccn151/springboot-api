package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.*;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.service.CartService;
import com.rockieslearning.crud.service.OrderService;
import com.rockieslearning.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;







    @GetMapping("")
    public ResponseEntity<UserDto> getUserById(HttpServletRequest request
                                               ) throws ResourceNotFoundException {

        Long userId = (Long) request.getAttribute("userId");
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }


    @GetMapping("/detail")
    public ResponseEntity<List<UserDetail>> getUserDetailById(HttpServletRequest request){

        Long userId = (Long) request.getAttribute("userId");
        List<UserDetail> userDetails =new ArrayList<>();
        userDetails = userService.getListDetailByUserId(userId);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);

    }



    @GetMapping("cart")
    public ResponseEntity<CartDto> getCartByUserId(HttpServletRequest request){

        Long userId = (Long) request.getAttribute("userId");
        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }



    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(HttpServletRequest request){

        Long userId = (Long) request.getAttribute("userId");
        List<OrderDto> orderDtos = orderService.getListOrderByUserId(userId);
        return new ResponseEntity<>(orderDtos,HttpStatus.OK);
    }





    @PostMapping("/cart")
    public ResponseEntity<CartDto> addCartQty(HttpServletRequest request
            , @RequestBody CartFoodDto cartFoodDto){

        Long userId = (Long) request.getAttribute("userId");
        CartDto cartResult = cartService.updateCart(userId, cartFoodDto);
        return new ResponseEntity<>(cartResult, HttpStatus.CREATED);
    }




    @PostMapping("orders")
    public ResponseEntity<OrderDto> addOrder(HttpServletRequest request,
                                             @RequestParam(required = false) boolean all,
                                             @RequestBody OrderDto orderDto){

        Long userId = (Long) request.getAttribute("userId");
        if(all){
            OrderDto order = orderService.createNewOrderFromCart(userId);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
        OrderDto order = orderService.createNewOrder(userId,orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


}
