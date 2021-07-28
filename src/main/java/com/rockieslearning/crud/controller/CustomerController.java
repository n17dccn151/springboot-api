package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.*;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.entity.UserDetailStatusName;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanVOD on Jul, 2021
 */


@CrossOrigin(origins = "*", maxAge = 3600)
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


    @GetMapping("/details")
    public ResponseEntity<List<UserDetailDto>> getUserDetailById(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        List<UserDetailDto> userDetailDtos = new ArrayList<>();
        userDetailDtos = userService.getListDetailByUserId(userId);
        return new ResponseEntity<>(userDetailDtos, HttpStatus.OK);

    }


    @PostMapping("/details")
    public ResponseEntity<UserDetailDto> addUserDetailById(HttpServletRequest request,
                                                           @RequestBody @Valid UserDetailDto userDetailDto) {

        Long userId = (Long) request.getAttribute("userId");
        UserDetailDto saveUserDetailDto = userService.saveUserDetail(userId, userDetailDto);
        return new ResponseEntity<>(saveUserDetailDto, HttpStatus.OK);

    }


    @PutMapping("/details/{detailId}")
    public ResponseEntity<UserDetailDto> updateUserDetailById(HttpServletRequest request,
                                                              @RequestParam(required = false) UserDetailStatusName status,
                                                              @PathVariable("detailId") Integer detailId,
                                                              @RequestBody(required = false)  @Valid UserDetailDto userDetailDto) {


        UserDetailDto saveUserDetailDto = new UserDetailDto();
        if(status!= null){
            System.out.println("_____________________");
             saveUserDetailDto = userService.updateUserDetailStatus(detailId, status);
        }else{
             saveUserDetailDto = userService.updateUserDetail(detailId, userDetailDto);
        }

        return new ResponseEntity<>(saveUserDetailDto, HttpStatus.OK);


    }

    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<Map<String, Boolean>> deleteUserDetailById(HttpServletRequest request,
                                                                     @PathVariable("detailId") Integer detailId) {


        userService.deleteUserDetail(detailId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);


    }


    @GetMapping("/cart")
    public ResponseEntity<CartDto> getCartByUserId(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }




    @PostMapping("/cart")
    public ResponseEntity<CartDto> addCartQty(HttpServletRequest request
            , @RequestBody @Valid CartFoodDto cartFoodDto) {

        Long userId = (Long) request.getAttribute("userId");
        CartDto cartDto = cartService.updateCart(userId, cartFoodDto);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }


    @PutMapping("/cart")
    public ResponseEntity<CartDto> editCartQty(HttpServletRequest request
            , @RequestBody @Valid CartFoodDto cartFoodDto) {

        Long userId = (Long) request.getAttribute("userId");
        CartDto cartDto = cartService.updateCart(userId, cartFoodDto);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        List<OrderDto> orderDtos = orderService.getListOrderByUserId(userId);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(HttpServletRequest request,
                                             @RequestParam(required = false) boolean all,
                                             @RequestBody(required = false) @Valid OrderDto orderDto) {

        Long userId = (Long) request.getAttribute("userId");
        if (all) {
            OrderDto order = orderService.createNewOrderFromCart(userId);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
        OrderDto order = orderService.createNewOrder(userId, orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }



    ////////////////
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(HttpServletRequest request,
                                                 @PathVariable("orderId") Integer orderId) {

        OrderDto orderDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

}
