package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rockieslearning.crud.entity.CartFood;
import com.rockieslearning.crud.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class CartDto {

    private Integer cartId;


    private Integer amount;


    private Set<CartFoodDto> cartFoods = new HashSet<CartFoodDto>();


    private Integer userId;


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<CartFoodDto> getCartFoods() {
        return cartFoods;
    }

    public void setCartFoods(Set<CartFoodDto> cartFoods) {
        this.cartFoods = cartFoods;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
