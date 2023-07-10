package com.sms.courseservice.controller;

import com.sms.courseservice.service.AcademicService;
import com.sms.enums.user_management.GradeEnum;
import com.sms.pojo.AcademicPojo;
import com.sms.response.SmsResponse;
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
@RequestMapping("/api/academics")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<?> getAcademicByUserId(@PathVariable("userId") Long userId){
        List<AcademicPojo> academics = academicService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academic",true,academics));
    }

    @GetMapping("fetchByGrade/{grade}")
    public ResponseEntity<?> getByGrade(@PathVariable("grade") GradeEnum grade){
        List<AcademicPojo> academics = academicService.getByGrade(grade);
        return ResponseEntity.status(HttpStatus.OK).body(academics);
    }
}