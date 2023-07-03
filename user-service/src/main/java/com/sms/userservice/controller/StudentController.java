package com.sms.userservice.controller;

import com.sms.response.SmsResponse;
import com.sms.userservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/student/")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @GetMapping("getById/{studentId}")
    public ResponseEntity<?> getById(@PathVariable("studentId") Long studentId){
        Map<String, Objects> student = studentService.getById(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, student));
    }
}
