package com.simplecrud.crud.exception;

import com.simplecrud.crud.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class UserNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)

        public Map<String,String> exceptionHandler(UserNotFoundException exception){
             Map<String,String> errorMap=new HashMap<>();
             errorMap.put("errorMessage" , exception.getMessage());

        return errorMap;
        }
    }

