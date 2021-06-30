package com.rockieslearning.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TanVOD on Jun, 2021
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class FaAuthException extends RuntimeException{

    public FaAuthException(String message){
        super(message);
    }

}
