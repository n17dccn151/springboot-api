package com.rockieslearning.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by TanVOD on Jul, 2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class ResponseDto {
    String error;
    String success;
    Object data;
}
