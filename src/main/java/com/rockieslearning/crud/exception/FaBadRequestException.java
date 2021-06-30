package com.rockieslearning.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TanVOD on Jun, 2021
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FaBadRequestException extends RuntimeException {
    public FaBadRequestException(String message){
        super(message);
    }
}
