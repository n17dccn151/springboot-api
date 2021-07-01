package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.FoodImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TanVOD on Jul, 2021
 */

@Component
public class FoodImageMapper {


    @Autowired
    FoodMapper foodMapper;


    public FoodImageDto toDto(FoodImage entity) {
        FoodImageDto dto = new FoodImageDto();
        dto.setId(entity.getId());
        dto.setUrl(entity.getImage());
        dto.setFood_id(entity.getFood().getFoodId());
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
        foodDto.setFoodId(dto.getFood_id());



        entity.setFood(foodMapper.toEntity(foodDto));



        return entity;
    }
}
