package com.rockieslearning.crud.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class CartDto {


    private Integer id;





    private Set<CartFoodDto> cartFoods = new HashSet<CartFoodDto>();


    private Long userId;


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
