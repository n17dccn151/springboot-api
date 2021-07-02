package com.rockieslearning.crud.dto;

/**
 * Created by TanVOD on Jun, 2021
 */


public class OrderFoodRequestDto {

    private Integer id;


    private Integer amount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
