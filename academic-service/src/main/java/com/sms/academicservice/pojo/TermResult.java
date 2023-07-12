package com.sms.academicservice.pojo;

import com.sms.enums.academic_management.TermEnum;
import com.sms.pojo.user_management.StudentPojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TermResult {
    StudentPojo student;
    private TermEnum term;
    private List<SubjectResult> academics;
    private Double percentage;
}
