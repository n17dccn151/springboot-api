package com.rockieslearning.crud.dto;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rockieslearning.crud.entity.*;
import com.rockieslearning.crud.repository.CategoryRepository;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.FoodService;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */


public class FoodDto {




    private Integer foodId;

    private String name;

    private Double price;

    private String description;

    private Float rating;

    private Integer categoryid;


    private List<FoodImageDto> images;


    public FoodDto toDto(Food entity) {
        FoodDto dto = new FoodDto();
        dto.setFoodId(entity.getFoodId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setCategoryid(entity.getCategory().getCategoryId());
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
        entity.setRating(dto.getRating());

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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public List<FoodImageDto> getImages() {
        return images;
    }

    public void setImages(List<FoodImageDto> images) {
        this.images = images;
    }
}
