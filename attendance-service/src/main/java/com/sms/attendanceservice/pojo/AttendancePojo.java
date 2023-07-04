package com.sms.attendanceservice.pojo;

import com.sms.model.user_management.User;
import lombok.*;

import java.util.List;


@Getter
@Setter

public class AttendancePojo {
    private Long attendanceId;

    private List<User> users;
    private String createdBy;
    private String updatedBy;

}
