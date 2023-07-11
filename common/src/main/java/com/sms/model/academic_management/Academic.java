package com.sms.model.academic_management;

import com.sms.enums.academic_management.TermEnum;
import com.sms.pojo.academic_management.AcademicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name="getAllByUserIdAndGrade",
        query="select academic_id as academicId,date,marks,term,course.subject,academic.user_id as userId,student_grade_history.grade from academic join course on academic.course_id=course.course_id join student_grade_history on academic.user_id=student_grade_history.user_id where student_grade_history.grade=:grade and academic.user_id=:userId",
        resultSetMapping = "Mapping.AcademicDto"
)
@SqlResultSetMapping(name="Mapping.AcademicDto",
        classes = @ConstructorResult(targetClass = AcademicDto.class,
                columns ={
                        @ColumnResult(name="academicId"),
                        @ColumnResult(name="date"),
                        @ColumnResult(name="marks"),
                        @ColumnResult(name="term"),
                        @ColumnResult(name="subject"),
                        @ColumnResult(name="userId"),
                        @ColumnResult(name="grade")
                })
)
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long academicId;

    private Long userId;

    @Max(100)
    private Integer marks;

    @PastOrPresent
    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private TermEnum term;
}