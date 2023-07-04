package com.sms.authservice.handler;

import com.sms.response.SmsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity.badRequest().body(new SmsResponse(usernameNotFoundException.getMessage(),false,null));
    }
}
