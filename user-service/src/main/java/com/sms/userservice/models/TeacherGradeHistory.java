package com.sms.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher_grade_history")
public class TeacherGradeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherGradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String grade;

    private Date date;
}
