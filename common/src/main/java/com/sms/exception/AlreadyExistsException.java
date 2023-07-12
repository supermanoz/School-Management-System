package com.sms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AlreadyExistsException extends RuntimeException{
    private static final Integer statusCode=HttpStatus.BAD_REQUEST.value();
    private String message;
    public AlreadyExistsException(String message){
        this.message=message;
    }
}