package com.sms.userservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPojo {

    private Long studentId;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String currentGrade;
}
