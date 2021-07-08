package com.rockieslearning.crud.dto;



import com.rockieslearning.crud.entity.*;

import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */


public class FoodDto {




    private Integer foodId;

    private String name;

    private Double price;

    private String description;


    private Integer categoryId;


    private List<FoodImageDto> images;


    public FoodDto toDto(Food entity) {
        FoodDto dto = new FoodDto();
        dto.setFoodId(entity.getFoodId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategory().getCategoryId());
        dto.setImages(new FoodImageDto().toListDto(entity.getImages()));
        return dto;
    }




    public List<FoodDto> toListDto(List<Food> listEntity) {
        List<FoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }

    public List<Food> toListEntity(List<FoodDto> listDto) {
        List<Food> listEti = new ArrayList<>();

        listDto.forEach(e->{
            listEti.add(this.toEntity(e));
        });
        return listEti;
    }




    public Food toEntity(FoodDto dto) {
        Food entity = new Food();
        //entity.setFoodId(dto.getFoodId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());

        return entity;
    }






    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<FoodImageDto> getImages() {
        return images;
    }

    public void setImages(List<FoodImageDto> images) {
        this.images = images;
    }
}
