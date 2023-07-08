package com.sms.attendanceservice.repository;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.pojo.DateTimePojo;
import com.sms.model.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

//   @Query("select a from Attendance a where a.checkIn =:checkIn")

   @Query(value = "SELECT * FROM attendance WHERE check_in>current_date() and user_id=:userId",nativeQuery = true)
   public Optional<Attendance>  getAttendanceByCheckInAndUserId(@Param("userId") Long userId);

//   @Query(value = "SELECT * FROM attendance WHERE user_id=:userId AND check_in",nativeQuery = true)
//   public Optional<Attendance> findByUserIdAndDate(@Param("userId") Long userId, LocalDate date);

   @Query(value = "SELECT * FROM attendance WHERE user_id=:userId", nativeQuery = true)
   public List<Attendance> findAttendanceByUserId(@Param("userId") Long userId);

   @Query(value = "SELECT * FROM attendance WHERE check_in BETWEEN :from and :to",nativeQuery = true)
   public Optional<List<Attendance>> getAllAttendanceBetweenDates(@Param("from") Date from,@Param("to") Date to);



   @Query(value = "SELECT * FROM attendance WHERE user_id=:userId AND check_in BETWEEN :from and :to " ,nativeQuery = true)
   public Optional<List<Attendance>> getAllAttendanceBetweenDatesByUserId(@Param("userId") Long userId,@Param("from") Date from,@Param("to") Date to);
}


