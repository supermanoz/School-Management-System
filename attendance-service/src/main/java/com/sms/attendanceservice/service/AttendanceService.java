package com.sms.attendanceservice.service;

import com.sms.attendanceservice.model.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance checkInAttendance(Long userId, Attendance attendance);
    List<Attendance> getAllAttendance();


}
