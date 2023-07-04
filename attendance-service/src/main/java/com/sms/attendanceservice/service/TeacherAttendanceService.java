package com.sms.attendanceservice.service;

import com.sms.attendanceservice.pojo.TeacherAttendancePojo;
import com.sms.attendanceservice.repository.TeacherAttendanceRepository;

import java.util.List;

public interface TeacherAttendanceService {

    public TeacherAttendancePojo createAttendance(TeacherAttendancePojo teacherAttendancePojo);
    public TeacherAttendancePojo updateAttendance(Long TeacherAttendanceId,TeacherAttendancePojo teacherAttendancePojo);
    public List<TeacherAttendancePojo> getAllAttendance();
    public TeacherAttendancePojo getById(Long teacherAttendanceId);
 }
