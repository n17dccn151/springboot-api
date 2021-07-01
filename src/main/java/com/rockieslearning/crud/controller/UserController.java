package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.UserRepository;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;




    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> userDtos = new ArrayList<>();
        userDtos = userService.retrieveUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(HttpServletRequest request,
                                                    @PathVariable("userId") Integer userId){

        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<UserDto> addUser(HttpServletRequest request, @RequestBody UserDto userDto) throws ParseException {

        UserDto saveUser =  userService.saveUser(userDto);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }




    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> updateUser(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId,
                                                               @RequestBody UserDto userDto){

        userService.updateUser(userId, userDto);


        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId) throws ParseException {

        userService.deleteUser(userId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
