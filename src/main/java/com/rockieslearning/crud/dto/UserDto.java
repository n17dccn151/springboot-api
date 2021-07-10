package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockieslearning.crud.entity.User;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

public class UserDto {


    private Long userId;

    @NumberFormat
    private String phone;

    @Email
    private String email;


    private String password;

    private Set<String> roles;

    //private Set<UserDetailDto> userDetailDto;

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());

        Set<String> roles = new HashSet<>();
        entity.getRoles().forEach(r -> {
            roles.add(r.getName().name());
        });

        dto.setRoles(roles);

        dto.setPassword(entity.getPassword());
        return dto;
    }


    public List<UserDto> toListDto(List<User> listEntity) {
        List<UserDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());


        entity.setPassword(dto.getPassword());
        return entity;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
