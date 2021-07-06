package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.service.CartService;
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
 * Created by TanVOD on Jul, 2021
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping("")
    public ResponseEntity<List<CartDto>> getAllCart(){

        List<CartDto> cartDtos = new ArrayList<>();
        cartDtos = cartService.retrieveCarts();
        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }




//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/{cartId}")
//    public ResponseEntity<CartDto> getCartById(HttpServletRequest request,
//                                                 @PathVariable("cartId") Integer cartId){
//
//
//        CartDto cartDto = cartService.getCartById(cartId);
//        return new ResponseEntity<>(cartDto,HttpStatus.OK);
//    }
//
//
//
//
//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @PostMapping("")
//    public ResponseEntity<CartDto> addCartQty(HttpServletRequest request
//            , @RequestBody CartFoodDto cartFoodDto){
//
//        int userId = (Integer) request.getAttribute("userId");
//        CartDto CartResult = cartService.updateCart(userId, cartFoodDto);
//        return new ResponseEntity<>(CartResult, HttpStatus.CREATED);
//    }






    @DeleteMapping("/{cartId}")
    public ResponseEntity<Map<String, Boolean>> deleteCart(HttpServletRequest request,
                                                            @PathVariable("cartId") Integer cartId) throws ParseException {

        cartService.deleteCart(cartId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
