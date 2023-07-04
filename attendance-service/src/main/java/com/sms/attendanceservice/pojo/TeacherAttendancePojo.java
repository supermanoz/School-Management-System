package com.sms.attendanceservice.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class TeacherAttendancePojo {

    private Long teacherAttendanceId;
    private Long teacherId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

}
