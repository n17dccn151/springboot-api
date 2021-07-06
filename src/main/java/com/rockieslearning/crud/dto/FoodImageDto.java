package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockieslearning.crud.entity.FoodImage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

public class FoodImageDto {



    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    private String url;

//    private Integer food_id;


    public FoodImageDto toDto(FoodImage entity) {
        FoodImageDto dto = new FoodImageDto();
        dto.setId(entity.getId());
        dto.setUrl(entity.getImage());
//        dto.setFood_id(entity.getFood().getFoodId());
        return dto;
    }


    public List<FoodImageDto> toListDto(List<FoodImage> listEntity) {
        List<FoodImageDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }

    public List<FoodImage> toListEntity(List<FoodImageDto> listDto) {
        List<FoodImage> listEti = new ArrayList<>();

        listDto.forEach(e->{
            listEti.add(this.toEntity(e));
        });
        return listEti;
    }


    public FoodImage toEntity(FoodImageDto dto) {
        FoodImage entity = new FoodImage();
        entity.setId(dto.getId());
        entity.setImage(dto.getUrl());

        FoodDto foodDto = new FoodDto();
//        foodDto.setFoodId(dto.getFood_id());



        entity.setFood(new FoodDto().toEntity(foodDto));



        return entity;
    }



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
