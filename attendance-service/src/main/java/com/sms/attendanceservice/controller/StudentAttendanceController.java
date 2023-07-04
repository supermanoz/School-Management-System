package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.pojo.StudentAttendancePojo;
import com.sms.attendanceservice.service.StudentAttendanceService;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
