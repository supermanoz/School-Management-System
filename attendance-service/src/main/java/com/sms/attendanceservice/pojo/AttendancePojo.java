package com.sms.attendanceservice.pojo;

import com.sms.model.user_management.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AttendancePojo implements Serializable {
    private Long attendanceId;
    private Long userId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String period;


}
