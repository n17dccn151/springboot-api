package com.rockieslearning.crud.dto;



import com.fasterxml.jackson.annotation.JsonView;
import com.rockieslearning.crud.entity.*;

import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */


public class FoodDto {




    @JsonView(View.Food.class)
    private Integer foodId;

    @JsonView(View.Food.class)
    private String name;

    @JsonView(View.Food.class)
    private Double price;

    @JsonView(View.Food.class)
    private Integer quantity;

    @JsonView(View.Food.class)
    private String status;


    @JsonView(View.Food.class)
    private String description;

    @JsonView(View.Food.class)
    private Integer categoryId;


    @JsonView(View.FoodWithImage.class)
    private List<FoodImageDto> images;

    @JsonView(View.FoodWithImageComment.class)
    private List<FoodRatingDto> rating;


    @JsonView(View.Food.class)
    private Double rate;

    public FoodDto toDto(Food entity) {
        FoodDto dto = new FoodDto();
        dto.setFoodId(entity.getFoodId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setStatus(entity.getFoodStatusName());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategory().getCategoryId());
        dto.setImages(new FoodImageDto().toListDto(entity.getImages()));
        dto.setRating(new FoodRatingDto().toListDto(entity.getRatings()));

        Double rate = 0.0;


        for(FoodRating foodRating : entity.getRatings()){
            rate+= foodRating.getRate();
        }


        dto.setRate(rate);
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
        entity.setQuantity(dto.getQuantity());
        return entity;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<FoodRatingDto> getRating() {
        return rating;
    }

    public void setRating(List<FoodRatingDto> rating) {
        this.rating = rating;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
