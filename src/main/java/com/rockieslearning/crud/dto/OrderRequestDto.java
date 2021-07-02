package com.rockieslearning.crud.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class OrderRequestDto {



    private Integer userId;

    private Set<OrderFoodRequestDto> orderFoods = new HashSet<OrderFoodRequestDto>();


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<OrderFoodRequestDto> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFoodRequestDto> orderFoods) {
        this.orderFoods = orderFoods;
    }
}
