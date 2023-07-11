package com.sms.attendanceservice.controller;

import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.pojo.DateTimePojo;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Deepak Saud
 */
@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * @param userId
     * @param request
     * @return
     * @Method checkInOutAttendance
     */
    @GetMapping("/checkInOut")
    public ResponseEntity<SmsResponse> checkInOutAttendance(@RequestParam("userId")  Long userId, HttpServletRequest request) {

        AttendancePojo checkInOutAttendance = attendanceService.checkInOutAttendance(userId, request);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully created")
                .payload(checkInOutAttendance)
                .build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * @return
     * @Method getAllAttendance
     */
    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllAttendance() {
        List<AttendancePojo> allAttendance = attendanceService.getAllAttendance();
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(allAttendance)
                .build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * @param userId
     * @return
     * @Method getAttendanceByUserId
     */
    @GetMapping("/fetchByUserId")
    public ResponseEntity<SmsResponse> getAttendanceByUserId(@RequestParam("userId") Long userId) {

        List<AttendancePojo> attendance = attendanceService.getAttendanceByUserId(userId);
        SmsResponse response = SmsResponse.builder()
                .message("successfully found")
                .status(true)
                .payload(attendance)
                .build();
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/fetchBetween")
    public ResponseEntity<SmsResponse> getAttendanceBetween(@RequestBody DateTimePojo dateTimePojo){

        List<AttendancePojo> attendancePojo = attendanceService.getAllAttendanceBetweenDates(dateTimePojo);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(attendancePojo)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/fetchBetweenByUserId")
    public ResponseEntity<SmsResponse> getAttendanceBetweenByUserId(@RequestParam("userId") Long userId, @RequestBody DateTimePojo dateTimePojo){

        List<AttendancePojo> attendancePojo = attendanceService.getAllAttendanceBetweenDatesByUserId(userId,dateTimePojo);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(attendancePojo)
                .build();
        return ResponseEntity.ok().body(response);
    }


    /**
     * @param attendanceId
     * @return
     */
    @GetMapping("/fetchById/{attendanceId}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long attendanceId) {

        AttendancePojo attendanceById = attendanceService.getByAttendanceId(attendanceId);
        SmsResponse response = SmsResponse.builder()
                .status(true)
                .message("successfully found")
                .payload(attendanceById)
                .build();

        return ResponseEntity.ok().body(response);
    }
}
