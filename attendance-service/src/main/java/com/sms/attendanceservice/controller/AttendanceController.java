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
    @PostMapping("/checkInOut")
    public ResponseEntity<?> checkInOutAttendance(@RequestParam( required = false) Long attendanceId, @RequestBody AttendancePojo attendancePojo){

        AttendancePojo checkInOutAttendance = attendanceService.checkInOutAttendance(attendanceId,attendancePojo);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully created")
                .payload(checkInOutAttendance)
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
      return ResponseEntity.ok().body(response);
    }

    @GetMapping("/fetchById/{attendanceId}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long attendanceId){

        AttendancePojo attendanceById = attendanceService.getByAttendanceId(attendanceId);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(attendanceById)
                .build();

        return ResponseEntity.ok().body(response);
    }
}
