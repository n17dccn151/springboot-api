package com.rockieslearning.crud.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */

@Getter
@Setter
public class ResponseMessage {

    private String message;
    private Object data;


    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
