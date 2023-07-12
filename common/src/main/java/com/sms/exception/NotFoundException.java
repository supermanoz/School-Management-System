package com.sms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends RuntimeException{
    private static final Integer statusCode=HttpStatus.NOT_FOUND.value();
    private String message;
    public NotFoundException(String message){
        this.message=message;
    }
}
