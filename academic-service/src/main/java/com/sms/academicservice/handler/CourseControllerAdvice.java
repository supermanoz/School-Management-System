package com.sms.academicservice.handler;

import com.sms.exception.NotFoundException;
import com.sms.response.SmsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<SmsResponse> notFoundExceptionHandler(NotFoundException notFoundException){
        return ResponseEntity.badRequest().body(new SmsResponse(notFoundException.getMessage(),false,null));
    }
}
