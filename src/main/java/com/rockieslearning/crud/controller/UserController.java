package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.exception.FaResourceNotFoundException;
import com.rockieslearning.crud.repository.UserRepository;
import com.rockieslearning.crud.service.UserService;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;


    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = new ArrayList<>();
        users = userService.retrieveUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(HttpServletRequest request,
                                                    @PathVariable("userId") Integer userId){


        User user = userService.getUserById(userId);
        if(user==null){
            throw new FaResourceNotFoundException("User not found");
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<User> addUser(HttpServletRequest request, @RequestBody User user){


        User userResult =  userService.saveUser(user);
        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }




    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId,
                                                               @RequestBody User user){

        User existUser = userService.getUserById(userId);
        if(user==null){
            throw new FaResourceNotFoundException("User not found");
        }

        existUser.setEmail(user.getEmail());
        existUser.setPhone(user.getPhone());
        existUser.setPassword(user.getPassword());
        existUser.setRoles(user.getRoles());

        userService.saveUser(existUser);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("userId") Integer userId){

        User user = userService.getUserById(userId);
        if(user==null){
            throw new FaResourceNotFoundException("User not found");
        }
        userService.deleteUser(user);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
