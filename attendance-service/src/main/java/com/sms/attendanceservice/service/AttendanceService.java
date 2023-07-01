package com.sms.attendanceservice.service;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    Attendance checkInAttendance(AttendancePojo attendancePojo);
    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByUserId(Long userId);

    List<Attendance> getAllAttendanceBetweenDates(String from, String to);

    List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId,String from, String to);

}
