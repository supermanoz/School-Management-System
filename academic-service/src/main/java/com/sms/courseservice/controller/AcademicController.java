package com.sms.courseservice.controller;

import com.sms.courseservice.service.AcademicService;
import com.sms.enums.user_management.GradeEnum;
import com.sms.pojo.AcademicDto;
import com.sms.pojo.AcademicPojo;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academics")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<SmsResponse> getAcademicByUserId(@PathVariable("userId") Long userId){
        List<AcademicPojo> academics = academicService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academic",true,academics));
    }

    @GetMapping("/fetchByGrade/{grade}")
    public ResponseEntity<SmsResponse> getByGrade(@PathVariable("grade") GradeEnum grade){
        List<AcademicPojo> academics = academicService.getByGrade(grade);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academic by grade",true,academics));
    }

    @GetMapping("/fetchByUserIdAndGrade")
    public ResponseEntity<SmsResponse> getByUserIdAndGrade(@RequestParam("userId") Long userId, @RequestParam("grade") GradeEnum grade){
        List<AcademicDto> academics = academicService.getByUserIdAndGrade(userId,grade);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academics by user id and grade",true,academics));
    }
}