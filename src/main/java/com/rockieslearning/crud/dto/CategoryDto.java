package com.rockieslearning.crud.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rockieslearning.crud.entity.Food;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */


public class CategoryDto {


    private Integer categoryId;


    private String name;


    private String description;


    private String image;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
