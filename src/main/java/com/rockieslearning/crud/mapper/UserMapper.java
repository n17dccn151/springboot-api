package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.UserDto;
import com.rockieslearning.crud.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Component
public class UserMapper {

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles());
        dto.setPassword(entity.getPassword());
        return dto;
    }


    public List<UserDto> toListDto(List<User> listEntity) {
        List<UserDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }


    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUserId(dto.getUserId());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRoles(dto.getRoles());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
