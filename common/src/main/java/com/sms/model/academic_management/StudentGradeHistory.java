package com.sms.model.academic_management;

import com.sms.enums.academic_management.GradeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student_grade_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private GradeEnum grade;
    private Date year;
    private Integer userId;
}
