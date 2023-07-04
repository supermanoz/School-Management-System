package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.pojo.TeacherAttendancePojo;
import com.sms.attendanceservice.service.TeacherAttendanceService;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances/teachers")
public class TeacherAttendanceController {

    @Autowired
    private TeacherAttendanceService teacherAttendanceService;

    @PostMapping("/checkIn")
    public ResponseEntity<?> teacherCheckIn(@RequestBody TeacherAttendancePojo teacherAttendancePojo){

       TeacherAttendancePojo teacherAttendancePojo1  = teacherAttendanceService.createAttendance(teacherAttendancePojo);

        SmsResponse response = SmsResponse.builder()
                .message("Check In")
                .status(true)
                .payload(teacherAttendancePojo1)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/checkOut/{teacherAttendanceId}")
    public ResponseEntity<?> teacherCheckOut(@PathVariable("teacherAttendanceId") Long teacherAttendanceId, @RequestBody TeacherAttendancePojo teacherAttendancePojo){

        TeacherAttendancePojo teacherAttendancePojo1  = teacherAttendanceService.updateAttendance(teacherAttendanceId, teacherAttendancePojo);

        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully checkout")
                .payload(teacherAttendancePojo1)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllTeacherAttendance(){

        List<TeacherAttendancePojo> teacherAttendancePojos = teacherAttendanceService.getAllAttendance();
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully Found")
                .payload(teacherAttendancePojos)
                .build();

        return ResponseEntity.ok().body(response);
    }
}
