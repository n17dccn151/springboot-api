package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockieslearning.crud.entity.Food;
import com.rockieslearning.crud.entity.Order;
import com.rockieslearning.crud.entity.OrderFood;
import com.rockieslearning.crud.entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */

public class OrderDto {


    private Long userId;

    private String status;


    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer orderId;


    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdDate;


    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedDate;


    @Size(min = 1, message
            = "About Me must be at least 1")
    private Set<OrderFoodDto> orderFoods = new HashSet<OrderFoodDto>();


    public OrderDto toDto(Order entity) {


        OrderDto dto = new OrderDto();
        dto.setOrderId(entity.getOrderId());
//        dto.setPrice(entity.getPrice());
//        dto.setAmount(entity.getAmount());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setUserId(entity.getUser().getUserId());


        Set<OrderFoodDto> orderFoodDtoSet = new HashSet<>();
        entity.getOrderFoods().forEach(e -> {
            orderFoodDtoSet.add(new OrderFoodDto().toDto(e));
        });

        dto.setOrderFoods(orderFoodDtoSet);


        return dto;
    }


    public List<OrderDto> toListDto(List<Order> listEntity) {
        List<OrderDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setOrderId(dto.getOrderId());
//        entity.setPrice(dto.getPrice());
//        entity.setAmount(dto.getAmount());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());
        return entity;
    }


    public OrderDto() {
    }


    public OrderDto(Long userId, String status, Integer orderId) {
        this.userId = userId;
        this.status = status;
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<OrderFoodDto> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFoodDto> orderFoods) {
        this.orderFoods = orderFoods;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
