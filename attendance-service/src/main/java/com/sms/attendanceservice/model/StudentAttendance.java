package com.sms.attendanceservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "student_attendance")
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentAttendanceId;
    private Long studentId;
    private LocalDate attendDate;

}
