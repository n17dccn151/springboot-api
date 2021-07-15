package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.UserDetailDto;
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
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> userDtos = new ArrayList<>();
        userDtos = userService.retrieveUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(HttpServletRequest request,
                                               @PathVariable("userId") Long userId) throws ResourceNotFoundException {

        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }


    @GetMapping("/{userId}/detail")
    public ResponseEntity<List<UserDetailDto>> getUserDetailById(HttpServletRequest request,
                                                                 @PathVariable("userId") Long userId) {
        List<UserDetailDto> userDetailDtos = new ArrayList<>();
        userDetailDtos = userService.getListDetailByUserId(userId);
        return new ResponseEntity<>(userDetailDtos, HttpStatus.OK);

    }


    @PostMapping("")
    public ResponseEntity<UserDto> addUser(HttpServletRequest request, @RequestBody UserDto userDto) throws ParseException {

        UserDto saveUser = userService.saveUser(userDto);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(HttpServletRequest request,
                                              @PathVariable("userId") Long userId,
                                              @RequestBody @Valid UserDto userDto) {

        UserDto updateUser = userService.updateUser(userId, userDto);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Long userId) throws ParseException {

        userService.deleteUser(userId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
