package com.sms.attendanceservice.service;
import com.sms.attendanceservice.model.StudentAttendance;
import com.sms.attendanceservice.pojo.StudentAttendancePojo;

import java.util.List;

public interface StudentAttendanceService {

    StudentAttendancePojo createStudentAttendance(StudentAttendancePojo studentAttendancePojo);
    List<StudentAttendancePojo> getAllStudentAttendance();





}
