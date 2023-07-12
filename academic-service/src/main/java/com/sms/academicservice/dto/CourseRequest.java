package com.sms.academicservice.dto;

import com.sms.enums.academic_management.GradeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseRequest {
    private String subject;
    private GradeEnum grade;
    private Long periodId;
}
