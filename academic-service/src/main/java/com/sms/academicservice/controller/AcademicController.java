package com.sms.academicservice.controller;

import com.sms.academicservice.dto.AcademicRequest;
import com.sms.academicservice.pojo.TermResult;
import com.sms.academicservice.service.AcademicService;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.enums.academic_management.TermEnum;
import com.sms.pojo.academic_management.AcademicPojo;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;

@RestController
@RequestMapping("/api/academics")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    @GetMapping("/fetch")
    public ResponseEntity<SmsResponse> getAcademicByUserId(@RequestParam("userId") Long userId, @RequestParam("term")TermEnum term){
        TermResult academics = academicService.getByUserIdAndTerm(userId,term);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academic",true,academics));
    }

    @GetMapping("/getGrade/{userId}")
    public ResponseEntity<SmsResponse> getGrade(@PathVariable("userId") Long userId){
        GradeEnum grade = academicService.getGrade(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Grade",true,grade));
    }

    @GetMapping("/fetchByGrade")
    public ResponseEntity<SmsResponse> getByGrade(@RequestParam("userId") Long userId, @RequestParam("term")TermEnum term,@RequestParam("grade")GradeEnum grade){
        TermResult academics = academicService.getByUserIdAndTermAndGrade(userId,term,grade);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academic by grade",true,academics));
    }

//    @GetMapping("/fetchByUserIdAndGrade")
//    public ResponseEntity<SmsResponse> getByUserIdAndGrade(@RequestParam("userId") Long userId, @RequestParam("grade") GradeEnum grade){
//        List<AcademicPojo> academics = academicService.getByUserIdAndGrade(userId,grade);
//        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academics by user id and grade",true,academics));
//    }

    @PostMapping("/save")
    public ResponseEntity<SmsResponse> saveAcademics(@RequestBody AcademicRequest academicRequest) throws InvalidAttributeValueException {
        AcademicPojo academic = academicService.save(academicRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("Academics inserted",true,academic));
    }
}