package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */


@Component
public class CategoryMapper {


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
        entity.setCategoryId(dto.getCategoryId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }
}
