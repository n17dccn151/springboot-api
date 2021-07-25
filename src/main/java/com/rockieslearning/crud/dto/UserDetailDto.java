package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.entity.UserDetail;
import com.rockieslearning.crud.entity.UserDetailStatusName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jun, 2021
 */

@Getter
@Setter
public class UserDetailDto {


    private Integer id;

    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;

    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;

    @NumberFormat
    private String phone;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    private Long userId;

    @NotNull
    private UserDetailStatusName status;


    public UserDetailDto toDto(UserDetail entity) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
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
        entity.setStatus(dto.getStatus());
        return entity;
    }


    public List<UserDetailDto> toListDto(List<UserDetail> listEntity) {
        List<UserDetailDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public UserDetailDto() {
    }



    public UserDetailDto(Integer id, String firstName, String lastName, String phone, String address, Long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.userId = userId;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}
