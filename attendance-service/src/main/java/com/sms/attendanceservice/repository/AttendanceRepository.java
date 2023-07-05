package com.sms.attendanceservice.repository;

import com.sms.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

//   @Query("select a from Attendance a where a.checkIn =:checkIn")

   @Query(value = "SELECT * FROM attendance WHERE check_in>current_date() and created_by=:createdBy",nativeQuery = true)
   public Optional<Attendance> getByCheckInAndCreatedBy(@Param("createdBy") String createdBy);

}


