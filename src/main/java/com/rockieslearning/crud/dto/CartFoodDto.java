package com.rockieslearning.crud.dto;

import com.rockieslearning.crud.entity.CartFood;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public class CartFoodDto {


    @NotNull(message = "foodId cannot be null")
    private Integer id;

    @Min(value = 0, message = "amount should not be less than 0")
    private Integer amount;

    private Double price;

    private String name;

    private String image;


    public CartFoodDto toDto(CartFood entity) {
        CartFoodDto dto = new CartFoodDto();
        dto.setId(entity.getFood().getFoodId());
        dto.setAmount(entity.getAmount());


        if (entity.getFood() != null) {
            dto.setName(entity.getFood().getName());
            dto.setImage(entity.getFood().getImages().get(0).getImage());
            dto.setPrice(entity.getFood().getPrice());
        }

        return dto;
    }


    public List<CartFoodDto> toListDto(List<CartFood> listEntity) {
        List<CartFoodDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
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


    public CartFoodDto() {
    }

    public CartFoodDto(Integer id, Integer amount, Double price, String name, String image) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.name = name;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
