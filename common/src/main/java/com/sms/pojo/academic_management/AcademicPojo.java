package com.sms.pojo.academic_management;

import com.sms.enums.academic_management.GradeEnum;
import com.sms.enums.academic_management.TermEnum;
import com.sms.pojo.user_management.UserPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AcademicPojo {

    private Long academicId;
    private UserPojo user;
    private Integer marks;
    private Date date;
    private String subject;
    private TermEnum term;
    private GradeEnum grade;
}
