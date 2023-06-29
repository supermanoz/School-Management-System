package com.example.academic.controller;


import com.example.academic.models.Academic;
import com.sms.model.user_management.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/academic/")
public class AcademicController {

    @GetMapping("{userId}")
    public ResponseEntity<?> getAcademic(@PathVariable("userId") Long userId){
        Academic academic = new Academic(userId, "admin");

        return ResponseEntity.status(HttpStatus.OK).body(academic);
    }
}
