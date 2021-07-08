package com.rockieslearning.crud.dto;

import com.rockieslearning.crud.entity.Cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class CartDto {


    private Integer id;


    private Set<CartFoodDto> cartFoods = new HashSet<CartFoodDto>();


    private Long userId;




    public CartDto toDto(Cart entity) {



        CartDto dto = new CartDto();
        dto.setId(entity.getCartId());
        dto.setUserId(entity.getUser().getUserId());


        Set<CartFoodDto> cartFoodDtoSet  = new HashSet<>();
        entity.getCartFoods().forEach(e->{
            cartFoodDtoSet.add(new CartFoodDto().toDto(e));
        });

        dto.setCartFoods(cartFoodDtoSet);



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
        entity.setCartId(dto.getId());

        return entity;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Set<CartFoodDto> getCartFoods() {
        return cartFoods;
    }

    public void setCartFoods(Set<CartFoodDto> cartFoods) {
        this.cartFoods = cartFoods;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
