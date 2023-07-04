package com.sms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AlreadyExistException extends RuntimeException{

    private static final Integer statusCode= HttpStatus.ALREADY_REPORTED.value();
    private String message;

    public AlreadyExistException(String message){
        this.message=message;
    }

}
