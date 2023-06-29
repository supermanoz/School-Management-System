package com.sms.model.user_management;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student_grade_history")
public class StudentGradeHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String grade;
    private Date year;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
