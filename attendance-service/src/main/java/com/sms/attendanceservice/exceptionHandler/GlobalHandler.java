package com.sms.attendanceservice.exceptionHandler;

//import com.sms.exception.AlreadyExistException;
import com.sms.exception.AlreadyExistException;
import com.sms.exception.NotFoundException;
import com.sms.response.SmsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException notFoundException){

        SmsResponse smsResponse = SmsResponse.builder()
                .status(false)
                .message(notFoundException.getMessage())
//                .payload(notFoundException)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(smsResponse);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> alreadyExistException(AlreadyExistException alreadyExistException){

        SmsResponse smsResponse = SmsResponse.builder()
                .status(false)
                .message(alreadyExistException.getMessage())
//                .payload(alreadyExistException)
                .build();

        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(smsResponse);
    }
}
