package com.sms.pojo.user_management;

import com.sms.enums.academic_management.GradeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentPojo {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private GradeEnum grade;
}
