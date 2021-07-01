package com.rockieslearning.crud.service;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.entity.Cart;
import com.rockieslearning.crud.mapper.CartMapper;
import com.rockieslearning.crud.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository repository;


    @Autowired
    private CartMapper mapper;


    public CartServiceImpl(CartRepository repository, CartMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public CartDto saveCart(CartDto CartDto) throws ParseException {
        Cart Cart = mapper.toEntity(CartDto);
        return mapper.toDto(repository.save(Cart));
    }

    @Override
    public List<CartDto> retrieveCarts() {
        List<Cart> Carts =  repository.findAll();
        return mapper.toListDto(Carts);
    }

    @Override
    public CartDto getCartById(int id) {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public void deleteCart(Integer CartId) throws ParseException {
        Cart Cart = repository.findById(CartId).get();
        repository.delete(Cart);
    }

    @Override
    public void updateCart(Integer CartId, CartDto CartDto) {



        Cart existCart = repository.findById(CartId).get();

        existCart.setAmount(CartDto.getAmount());

        //existCart.setCartFoods(CartDto.getCartFoods());
    }

}
