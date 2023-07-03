package com.sms.userservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPojo {


    private Long teacherId;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String assignedGrade;

    private Integer currentSalary;
}
