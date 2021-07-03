package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.exception.ResourceNotFoundException;
import com.rockieslearning.crud.service.CartService;
import com.rockieslearning.crud.service.OrderService;
import com.rockieslearning.crud.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;





    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> userDtos = new ArrayList<>();
        userDtos = userService.retrieveUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(HttpServletRequest request,
                                                    @PathVariable("userId") Integer userId) throws ResourceNotFoundException {

        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}/detail")
    public ResponseEntity<List<UserDetail>> getUserDetailById(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId){
        List<UserDetail> userDetails =new ArrayList<>();
        userDetails = userService.getListDetailByUserId(userId);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{userId}/cart")
    public ResponseEntity<CartDto> getCartByUserId(HttpServletRequest request,
                                                   @PathVariable("userId") Integer userId){

        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{userId}/orders")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(HttpServletRequest request,
                                                          @PathVariable("userId") Integer userId){

        List<OrderDto> orderDtos = orderService.getListOrderByUserId(userId);
        return new ResponseEntity<>(orderDtos,HttpStatus.OK);
    }





    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<UserDto> addUser(HttpServletRequest request, @RequestBody UserDto userDto) throws ParseException {

        UserDto saveUser =  userService.saveUser(userDto);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }




    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> updateUser(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId,
                                                               @RequestBody UserDto userDto){

        userService.updateUser(userId, userDto);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId) throws ParseException {

        userService.deleteUser(userId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
