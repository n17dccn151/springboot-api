package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CartDto;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
public interface CartService {

    public CartDto saveCart(CartDto CartDto) throws ParseException;

    public List<CartDto> retrieveCarts();

    public CartDto getCartById(int id) ;

    public void deleteCart(Integer CartId) throws ParseException;

    public void updateCart(Integer CartId, CartDto  CartDto);


}
