package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.OrderFoodDto;
import com.rockieslearning.crud.entity.OrderFood;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Component
public class OrderFoodMapper {

    public OrderFoodDto toDto(OrderFood entity) {
        OrderFoodDto dto = new OrderFoodDto();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setAmount(entity.getAmount());



        if(entity.getFood()!=null){
            dto.setName(entity.getFood().getName());
            dto.setImage(entity.getFood().getImages().get(0).getImage());
        }

        return dto;
    }


    public List<OrderFoodDto> toListDto(List<OrderFood> listEntity) {
        List<OrderFoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });
        return listDto;
    }


    public OrderFood toEntity(OrderFoodDto dto) {
        OrderFood entity = new OrderFood();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setAmount(dto.getAmount());

        return entity;
    }
}
