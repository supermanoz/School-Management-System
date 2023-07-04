package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.pojo.StudentAttendancePojo;
import com.sms.attendanceservice.service.StudentAttendanceService;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances/students")
public class StudentAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @PostMapping("/checkIn")
    public ResponseEntity<?> createStudentAttendance(@RequestBody StudentAttendancePojo studentAttendancePojo){

        StudentAttendancePojo studentAttendance = studentAttendanceService.createStudentAttendance(studentAttendancePojo);
        SmsResponse response = SmsResponse.builder()
                .message("successfully found")
                .status(true)
                .payload(studentAttendance)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllStudentAttendance(){

        List<StudentAttendancePojo> studentAttendancePojos = studentAttendanceService.getAllStudentAttendance();
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(studentAttendancePojos)
                .build();


        return ResponseEntity.ok().body(response);
    }
}
