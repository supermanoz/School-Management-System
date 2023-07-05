package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.service.AttendanceService;

import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;


    @PostMapping("/checkIn")
    public ResponseEntity<?> createAttendance(@RequestBody AttendancePojo attendancePojo){

        AttendancePojo checkInAttendance = attendanceService.checkInAttendance(attendancePojo);
       SmsResponse response = SmsResponse.builder()
               .status(true)
               .message("successfully Created")
               .payload(checkInAttendance)
               .build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/checkOut/{attendanceId}")
    public ResponseEntity<?> createAttendance(@PathVariable("attendanceId") Long attendanceId, @RequestBody AttendancePojo attendancePojo){

        AttendancePojo checkOutAttendance = attendanceService.chekOutAttendance(attendanceId,attendancePojo);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully checkout")
                .payload(checkOutAttendance)
                .build();

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllAttendance(){
      List<AttendancePojo> allAttendance =  attendanceService.getAllAttendance();
      SmsResponse response = SmsResponse.builder()
              .status(true)
              .message("successfully found")
              .payload(allAttendance)
              .build();
      return ResponseEntity.ok().body(response.getPayload());
    }
}
