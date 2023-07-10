package com.sms.courseservice.controller;

import com.sms.courseservice.service.PeriodService;
import com.sms.model.user_management.Period;
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
}