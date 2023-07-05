package com.sms.attendanceservice.pojo;

import com.sms.model.user_management.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AttendancePojo {
    private Long attendanceId;
    private Long userId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String subjectCode;




}
