package com.sms.attendanceservice.service;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    AttendancePojo checkInAttendance(AttendancePojo attendancePojo);

    AttendancePojo chekOutAttendance(Long attendanceId, AttendancePojo attendancePojo);


    List<AttendancePojo> getAllAttendance();

    List<Attendance> getAttendanceByUserId(Long userId);

    List<Attendance> getAllAttendanceBetweenDates(String from, String to);

    List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId,String from, String to);

}
