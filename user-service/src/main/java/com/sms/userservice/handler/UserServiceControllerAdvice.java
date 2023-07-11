package com.sms.userservice.handler;

import com.sms.exception.NotFoundException;
import com.sms.response.SmsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class UserServiceControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementExceptionHandler(NoSuchElementException noSuchElementException){
        return ResponseEntity.ok().body(new SmsResponse("No Such Element", false,noSuchElementException.getMessage()));
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<?> notFoundExceptionHandler(NotFoundException notFoundException){
//        return ResponseEntity.ok().body(new SmsResponse(HttpStatus.NO_CONTENT.name(), false,notFoundException.getMessage()));
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> globalExceptionHandler(Exception exception){
//        return ResponseEntity.badRequest().body(new SmsResponse(HttpStatus.BAD_REQUEST.name(), false,exception.getMessage()));
//    }
}
