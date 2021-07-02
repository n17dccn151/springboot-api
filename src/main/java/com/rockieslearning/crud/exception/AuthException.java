package com.rockieslearning.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TanVOD on Jun, 2021
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException{

    public AuthException(String message){
        super(message);
    }

}
