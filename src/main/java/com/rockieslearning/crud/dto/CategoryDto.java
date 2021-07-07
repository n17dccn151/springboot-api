package com.rockieslearning.crud.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rockieslearning.crud.entity.Category;
import com.rockieslearning.crud.entity.Food;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */


public class CategoryDto {


    private Integer categoryId;


    private String name;


    private String description;


    private String image;


    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(entity.getCategoryId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());

        return dto;
    }


    public List<CategoryDto> toListDto(List<Category> listEntity) {
        List<CategoryDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Category toEntity(CategoryDto dto) {
        Category entity = new Category();
        //entity.setCategoryId(dto.getCategoryId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }


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
