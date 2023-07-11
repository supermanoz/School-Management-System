package com.sms.pojo;

import com.sms.enums.user_management.GradeEnum;
import com.sms.enums.user_management.TermEnum;
import com.sms.model.user_management.Course;
import com.sms.model.user_management.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PastOrPresent;
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
