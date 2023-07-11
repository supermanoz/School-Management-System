package com.sms.academicservice.dto;

import com.sms.enums.academic_management.TermEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRequest {
    private Long academicId;
    private Long userId;
    @Max(100)
    private Integer marks;
    private Long courseId;
    private TermEnum term;
}
