package com.rockieslearning.crud.mapper;

import com.rockieslearning.crud.dto.UserDetailDto;
import com.rockieslearning.crud.entity.UserDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Component
public class UserDetailMapper {

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



}
