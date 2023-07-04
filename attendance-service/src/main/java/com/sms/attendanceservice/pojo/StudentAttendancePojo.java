package com.sms.attendanceservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendancePojo {

    private Long studentAttendanceId;
    private Long studentId;
    private LocalDate attendDate;
}
