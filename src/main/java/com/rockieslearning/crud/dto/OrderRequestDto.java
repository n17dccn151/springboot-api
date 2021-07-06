package com.rockieslearning.crud.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class OrderRequestDto {



    private Long userId;

    private String status;

    private Set<OrderFoodRequestDto> orderFoods = new HashSet<OrderFoodRequestDto>();






    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<OrderFoodRequestDto> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFoodRequestDto> orderFoods) {
        this.orderFoods = orderFoods;
    }
}
