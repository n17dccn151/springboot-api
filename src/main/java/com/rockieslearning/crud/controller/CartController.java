package com.rockieslearning.crud.controller;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.service.CartService;
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

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(HttpServletRequest request,
                                                 @PathVariable("cartId") Integer cartId){


        CartDto cartDto = cartService.getCartById(cartId);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

/*
    @PostMapping("")
    public ResponseEntity<Cart> addCart(HttpServletRequest request, @RequestBody Map<String, Object> CartMap){





        Integer userId = Integer.parseInt(CartMap.get("userId").toString());
        Double price = Double.parseDouble(CartMap.get("price").toString());
        Integer amount = Integer.parseInt(CartMap.get("amount").toString());
        String status = (String) CartMap.get("status");
        Integer foodId = Integer.parseInt(CartMap.get("foodId").toString());





        User user = userService.getUserById(userId);
        if(user==null){
            throw new FaResourceNotFoundException("user not found");
        }

        Food food = foodService.getFoodById(foodId);
        if(food==null){
            throw new FaResourceNotFoundException("food not found");
        }

        Cart Cart = new Cart();
        Cart.setAmount(amount);
        Cart.setPrice(price);
        Cart.setStatus(status);
        Cart.setUser(user);
        //

        CartFood  CartFood = new CartFood();
        CartFood.setFood(food);
        CartFood.setCart(Cart);
//        CartFood


        Cart CartResult =  CartService.saveCart(Cart);
        return new ResponseEntity<>(CartResult, HttpStatus.CREATED);
    }


*/

//    @PutMapping("/{CartId}")
//    public ResponseEntity<Map<String, Boolean>> updateCart(HttpServletRequest request,
//                                                               @PathVariable("CartId") Integer CartId,
//                                                               @RequestBody Cart Cart){
//        Cart existCart = CartService.getCartById(CartId);
//        if(existCart==null){
//            throw new FaResourceNotFoundException("Cart not found");
//        }
//        existCart.setPrice(Cart.getPrice());
//        existCart.setAmount(Cart.getAmount());
//        existCart.setStatus(Cart.getStatus());
//        existCart.setCartFoods(Cart.getCartFoods());
//
//
//        Map<String, Boolean> map = new HashMap<>();
//        map.put("success", true);
//        return new ResponseEntity<>(map, HttpStatus.OK);
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
