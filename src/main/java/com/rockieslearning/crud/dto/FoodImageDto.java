package com.rockieslearning.crud.dto;

/**
 * Created by TanVOD on Jun, 2021
 */

public class FoodImageDto {



    private Integer id;

    private String url;

//    private Integer food_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public Integer getFood_id() {
//        return food_id;
//    }
//
//    public void setFood_id(Integer food_id) {
//        this.food_id = food_id;
//    }
}
