package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockieslearning.crud.entity.OrderFood;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public class OrderFoodDto {

    @NotNull(message = "id cannot be null")
    private Integer id;

    @Size(min = 1, message
            = "About Me must be at least 1")
    private Integer amount;


    private Double price;


    private String name;


    private String image;


//    private OrderDto orderDto;
//
//
//    private FoodDto foodDto;


    public OrderFoodDto toDto(OrderFood entity) {
        OrderFoodDto dto = new OrderFoodDto();
        dto.setId(entity.getFood().getFoodId());
        dto.setPrice(entity.getPrice());
        dto.setAmount(entity.getAmount());


        if (entity.getFood() != null) {
            dto.setName(entity.getFood().getName());
            dto.setImage(entity.getFood().getImages().get(0).getImage());
        }

        return dto;
    }


    public List<OrderFoodDto> toListDto(List<OrderFood> listEntity) {
        List<OrderFoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
