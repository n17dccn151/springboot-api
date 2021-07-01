package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */


public class FoodRatingDto {


    private Integer id;

    private String rate;

    private Food food;

    private UserDto userDto;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
