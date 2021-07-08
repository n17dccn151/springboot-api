package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */


public class UserDetailDto {


    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private Long userId;




    public UserDetailDto toDto(UserDetail entity) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserId(entity.getUser().getUserId());
        return dto;
    }


    public UserDetail toEnti(UserDetailDto dto) {
        UserDetail entity = new UserDetail();

        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        return entity;
    }


    public List<UserDetailDto> toListDto(List<UserDetail> listEntity) {
        List<UserDetailDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
