package com.rockieslearning.crud.exception;

import com.rockieslearning.crud.controller.CategoryController;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TanVOD on Jul, 2021
 */


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, Object> map = new HashMap<>();

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        map.put("message", errors);
        return map;
    }





    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> map = new HashMap<>();


        map.put("message", ex.getMessage());
        return map;
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, Object> handleBadRequestException(BadRequestException ex) {
        Map<String, Object> map = new HashMap<>();

        map.put("message", ex.getMessage());
        return map;
    }


}
