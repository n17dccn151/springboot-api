package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.CartFoodDto;
import com.rockieslearning.crud.entity.CartFood;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */


@Component
public class CartFoodMapper {
    public CartFoodDto toDto(CartFood entity) {
        CartFoodDto dto = new CartFoodDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());



        if(entity.getFood()!=null){
            dto.setFoodName(entity.getFood().getName());
            dto.setFoodImage(entity.getFood().getImages().get(0).getImage());
        }

        return dto;
    }


    public List<CartFoodDto> toListDto(List<CartFood> listEntity) {
        List<CartFoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });
        return listDto;
    }


    public CartFood toEntity(CartFoodDto dto) {
        CartFood entity = new CartFood();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());

        return entity;
    }

}
