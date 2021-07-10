package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.exception.BadRequestException;
import com.rockieslearning.crud.exception.ResourceNotFoundException;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
public interface CartService {

    public CartDto saveCart(CartDto CartDto) throws BadRequestException;

    public List<CartDto> retrieveCarts();

    public CartDto getCartById(int id) throws ResourceNotFoundException;

    public String deleteCart(Integer CartId) throws ResourceNotFoundException;

    public CartDto getCartByUserId(Long id) throws ResourceNotFoundException;


    CartDto updateCart(Long userId, CartFoodDto cartFoodDto);

}
