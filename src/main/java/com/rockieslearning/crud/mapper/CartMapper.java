package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.CartDto;
import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TanVOD on Jul, 2021
 */

@Component
public class CartMapper {

    @Autowired
    private CartFoodMapper CartFoodMapper;


    public CartDto toDto(Cart entity) {



        CartDto dto = new CartDto();
        dto.setCartId(entity.getCartId());
        dto.setAmount(entity.getAmount());
        dto.setUserId(entity.getUser().getUserId());


        Set<CartFoodDto> CartFoodDtoSet  = new HashSet<>();
        entity.getCartFoods().forEach(e->{
            CartFoodDtoSet.add(CartFoodMapper.toDto(e));
        });

        dto.setCartFoods(CartFoodDtoSet);



        return dto;
    }


    public List<CartDto> toListDto(List<Cart> listEntity) {
        List<CartDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Cart toEntity(CartDto dto) {
        Cart entity = new Cart();
        entity.setCartId(dto.getCartId());
        entity.setAmount(dto.getAmount());
        return entity;
    }
}
