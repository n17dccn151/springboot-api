package com.rockieslearning.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TanVOD on Jun, 2021
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FaResourceNotFoundException extends RuntimeException {


    public FaResourceNotFoundException(String message){
        super(message);
    }
}
