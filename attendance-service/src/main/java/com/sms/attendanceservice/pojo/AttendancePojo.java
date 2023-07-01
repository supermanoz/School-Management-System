package com.sms.attendanceservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancePojo {

    private Long attendanceId;
    private List<Long> users;
}
