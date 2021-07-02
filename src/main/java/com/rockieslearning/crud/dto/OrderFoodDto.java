package com.rockieslearning.crud.dto;

/**
 * Created by TanVOD on Jun, 2021
 */


public class OrderFoodDto {

    private Integer id;


    private Double price;


    private Integer amount;


    private String name;

    private String image;



//    private OrderDto orderDto;
//
//
//    private FoodDto foodDto;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
