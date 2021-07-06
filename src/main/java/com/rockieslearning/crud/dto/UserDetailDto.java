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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public UserDetailDto toDto(UserDetail entity) {
        UserDetailDto dto = new UserDetailDto();
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        return dto;
    }


    public List<UserDetailDto> toListDto(List<UserDetail> listEntity) {
        List<UserDetailDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
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
