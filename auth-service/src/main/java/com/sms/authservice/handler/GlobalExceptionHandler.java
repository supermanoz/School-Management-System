package com.sms.authservice.handler;

import com.sms.exception.NotFoundException;
import com.sms.response.SmsResponse;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity.badRequest().body(new SmsResponse(usernameNotFoundException.getMessage(),false,null));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandler(NotFoundException notFoundException){
        return ResponseEntity.badRequest().body(new SmsResponse(notFoundException.getMessage(),false,null));
    }
}
