package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.repository.user_management.UserRepository;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/test")
    public String test() {
            return ("Test working");
    }


//    @PostMapping("/save")
//    public ResponseEntity<?> createAttendance(@PathVariable , @RequestBody Attendance attendance){
//
//        Attendance attendance1 = attendanceService.addAttendance(attendance);
//
//        SmsResponse<Attendance> attendanceSmsResponse = new SmsResponse<>(HttpStatus.CREATED.value(),HttpStatus.CREATED.name(),true, attendance1);
//
//        return ResponseEntity.ok().body(attendanceSmsResponse);
//
//    }


    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllAttendance(){
      List<Attendance> allAttendance =  attendanceService.getAllAttendance();

      SmsResponse<List<Attendance>> smsResponse = new SmsResponse<List<Attendance>>(HttpStatus.OK.value(), HttpStatus.OK.name(), true, allAttendance);

      return ResponseEntity.ok().body(smsResponse);
    }

//    @PostMapping("/check/in/{userId}")
//    public ResponseEntity<?> checkIn(@PathVariable("userId") Long userId, @RequestBody Attendance attendance){
//
//        Attendance todayAttendance = attendanceService.addAttendance();
//
//        SmsResponse<Attendance> attendanceSmsResponse = new SmsResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), true,todayAttendance);
//
//        return ResponseEntity.ok().body(attendanceSmsResponse);
//    }
}
