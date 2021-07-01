package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.OrderDto;
import com.rockieslearning.crud.dto.OrderFoodDto;
import com.rockieslearning.crud.entity.Order;
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
public class OrderMapper {


    @Autowired
    private OrderFoodMapper orderFoodMapper;


    public OrderDto toDto(Order entity) {



        OrderDto dto = new OrderDto();
        dto.setOrderId(entity.getOrderId());
        dto.setPrice(entity.getPrice());
        dto.setAmount(entity.getAmount());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setUserId(entity.getUser().getUserId());


        Set<OrderFoodDto> orderFoodDtoSet  = new HashSet<>();
        entity.getOrderFoods().forEach(e->{
            orderFoodDtoSet.add(orderFoodMapper.toDto(e));
        });

        dto.setOrderFoods(orderFoodDtoSet);



        return dto;
    }


    public List<OrderDto> toListDto(List<Order> listEntity) {
        List<OrderDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setOrderId(dto.getOrderId());
        entity.setPrice(dto.getPrice());
        entity.setAmount(dto.getAmount());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        return entity;
    }
}
