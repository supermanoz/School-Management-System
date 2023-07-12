package com.sms.academicservice.handler;

import com.sms.exception.AlreadyExistsException;
import com.sms.exception.NotFoundException;
import com.sms.response.SmsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.InvalidAttributeValueException;

@ControllerAdvice
public class CourseControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<SmsResponse> notFoundExceptionHandler(NotFoundException notFoundException){
        return ResponseEntity.badRequest().body(new SmsResponse(notFoundException.getMessage(),false,null));
    }

    @ExceptionHandler(InvalidAttributeValueException.class)
    public ResponseEntity<SmsResponse> invalidAttributeValueExceptionHandler(InvalidAttributeValueException invalidAttributeValueException){
        return ResponseEntity.badRequest().body(new SmsResponse(invalidAttributeValueException.getMessage(),false,null));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<SmsResponse> alreadyExistsExceptionHandler(AlreadyExistsException alreadyExistsException){
        return ResponseEntity.badRequest().body(new SmsResponse(alreadyExistsException.getMessage(),false,null));
    }
}
