package com.example.academic.controller;


import com.example.academic.models.Academic;
import com.example.academic.services.AcademicService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/academic/")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @GetMapping("{userId}")
    public ResponseEntity<?> getAcademicByUserId(@PathVariable("userId") Long userId){
        List<Map<String, Objects>> academics = academicService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(academics);
    }

    @GetMapping("getByGrade/{grade}")
    public ResponseEntity<?> getByGrade(@PathVariable("grade") String grade){
        List<Map<String,Objects>> result = academicService.getByGrade(grade);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
