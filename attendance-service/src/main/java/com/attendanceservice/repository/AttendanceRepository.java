package com.attendanceservice.repository;

import com.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {

}
