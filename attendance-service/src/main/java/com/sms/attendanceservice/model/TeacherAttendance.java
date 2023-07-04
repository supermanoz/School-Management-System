package com.sms.attendanceservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TeacherAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherAttendanceId;
    private Long teacherId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

}
