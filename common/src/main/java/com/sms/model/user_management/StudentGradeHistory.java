package com.sms.model.user_management;

import com.sms.enums.user_management.GradeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student_grade_history")
public class StudentGradeHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private GradeEnum grade;
    private Date year;
    private Integer userId;
}
