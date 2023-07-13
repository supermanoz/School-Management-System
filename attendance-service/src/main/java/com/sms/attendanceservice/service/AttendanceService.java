package com.sms.attendanceservice.service;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.pojo.DateTimePojo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public interface AttendanceService {

    AttendancePojo checkInOutAttendance(Long userId, HttpServletRequest request);

    List<AttendancePojo> getAllAttendance();

    List<AttendancePojo> getAttendanceByUserId(Long userId);

    AttendancePojo getByAttendanceId(Long attendanceId);

    List<AttendancePojo> getAllAttendanceBetweenDates(DateTimePojo dateTimePojo);

    List<AttendancePojo> getAllAttendanceBetweenDatesByUserId(Long userId,DateTimePojo dateTimePojo);

    List<AttendancePojo> getAllAttendanceByPeriod(String period);

}
