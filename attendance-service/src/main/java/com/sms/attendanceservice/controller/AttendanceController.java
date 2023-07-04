package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.service.AttendanceService;

import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;


//    @PostMapping("/checkIn")
//    public ResponseEntity<?> createAttendance(@RequestBody AttendancePojo attendancePojo){
//
//        Optional<Attendance> checkInAttendance = attendanceService.checkInAttendance(attendancePojo);
//       SmsResponse response = SmsResponse.builder()
//               .status(true)
//               .message("successfully Created")
//               .payload(checkInAttendance)
//               .build();
//
//        return ResponseEntity.ok().body(response);
//    }

    @PutMapping("/checkout/{attendanceId}")
    public ResponseEntity<?> updateAttendance(@PathVariable("attendanceId") Long attendanceId, @RequestBody Attendance attendance){

        Attendance chekOutAttendance = attendanceService.chekOutAttendance(attendanceId,attendance);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully updated")
                .payload(chekOutAttendance)
                .build();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllAttendance(){
      List<Attendance> allAttendance =  attendanceService.getAllAttendance();
      SmsResponse response = SmsResponse.builder()
              .status(true)
              .message("successfully found")
              .payload(allAttendance)
              .build();
      return ResponseEntity.ok().body(response.getPayload());
    }
}
