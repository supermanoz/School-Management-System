package com.sms.pojo;

import com.sms.enums.user_management.GradeEnum;
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
