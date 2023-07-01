package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.service.AttendanceService;

import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;



    @PostMapping("/save")
    public ResponseEntity<?> createAttendance(@RequestBody AttendancePojo attendancePojo){

        Attendance attendance1 = attendanceService.checkInAttendance(attendancePojo);
       SmsResponse builder = SmsResponse.builder()
               .status(true)
               .message("successfully Created")
               .payload(attendance1)
               .build();

        return ResponseEntity.ok().body(builder);
    }


    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllAttendance(){
      List<Attendance> allAttendance =  attendanceService.getAllAttendance();
      SmsResponse responseAttendance = SmsResponse.builder()
              .status(true)
              .message("successfully found")
              .payload(allAttendance)
              .build();


      return ResponseEntity.ok().body(responseAttendance);
    }


}
