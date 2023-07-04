package com.sms.attendanceservice.repository;

import com.sms.attendanceservice.model.StudentAttendance;
import com.sms.attendanceservice.pojo.StudentAttendancePojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {


    public Optional<StudentAttendance> findByAttendDateAndStudentId(LocalDate attendDate, Long studentId);
}
