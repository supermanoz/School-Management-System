package com.sms.pojo.academic_management;

import com.sms.enums.academic_management.GradeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoursePojo {
    private Long courseId;
    private String subject;
    private GradeEnum grade;
    private PeriodPojo period;
}
