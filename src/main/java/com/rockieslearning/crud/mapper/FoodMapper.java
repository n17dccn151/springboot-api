package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
@Component
public class FoodMapper {


    @Autowired
    FoodImageMapper foodImageMapper;



    public FoodDto toDto(Food entity) {
        FoodDto dto = new FoodDto();
        dto.setFoodId(entity.getFoodId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setCategoryid(entity.getCategory().getCategoryId());

        dto.setImages(foodImageMapper.toListDto(entity.getImages()));
        return dto;
    }


    public List<FoodDto> toListDto(List<Food> listEntity) {
        List<FoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Food toEntity(FoodDto dto) {
        Food entity = new Food();
        entity.setFoodId(dto.getFoodId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setRating(dto.getRating());



        return entity;
    }
    
}
