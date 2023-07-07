package com.sms.attendanceservice.service;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public interface AttendanceService {

    Attendance checkInOutAttendance(Long userId, HttpServletRequest request);

    List<AttendancePojo> getAllAttendance();

    List<AttendancePojo> getAttendanceByUserId(Long userId);

    AttendancePojo getByAttendanceId(Long attendanceId);



    List<Attendance> getAllAttendanceBetweenDates(String from, String to);

    List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId,String from, String to);

}
