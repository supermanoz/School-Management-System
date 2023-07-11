package com.sms.academicservice.controller;

import com.sms.academicservice.service.PeriodService;
import com.sms.model.academic_management.Period;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/periods")
public class PeriodsController {
    @Autowired
    private PeriodService periodService;
    @PostMapping("/save")
    public ResponseEntity<SmsResponse> addPeriod(@RequestBody Period period){
        return ResponseEntity.ok().body(new SmsResponse("Period created successfully!",true,periodService.save(period)));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<SmsResponse> fetchAll(){
        return ResponseEntity.ok().body(new SmsResponse("List of periods",true,periodService.fetchAll()));
    }

    @GetMapping("/fetchByCurrentTime")
    public ResponseEntity<SmsResponse> fetchByCurrentTime(){
        return ResponseEntity.ok().body(new SmsResponse("Period of current time",true,periodService.getByCurrentTime()));
    }
}
