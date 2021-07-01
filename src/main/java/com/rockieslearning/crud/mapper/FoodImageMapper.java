package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.FoodImageDto;
import com.rockieslearning.crud.entity.FoodImage;
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

    public FoodImageDto toDto(FoodImage entity) {
        FoodImageDto dto = new FoodImageDto();
        dto.setId(entity.getId());
        dto.setUrl(entity.getImage());
        return dto;
    }


    public List<FoodImageDto> toListDto(List<FoodImage> listEntity) {
        List<FoodImageDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public FoodImage toEntity(FoodImageDto dto) {
        FoodImage entity = new FoodImage();
        entity.setId(dto.getId());
        entity.setImage(dto.getUrl());


        return entity;
    }
}
