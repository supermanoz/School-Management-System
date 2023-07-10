package com.sms.courseservice.repository;

import com.sms.enums.user_management.GradeEnum;
import com.sms.model.user_management.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {
    @Query(value = "select academic_id as academicId,date,marks,term,course.subject,academic.user_id as userId,student_grade_history.grade from academic join course on academic.course_id=course.course_id join student_grade_history on academic.user_id=student_grade_history.user_id where academic.user_id=?1",nativeQuery = true)
    List<Map<String,Object>> findByUserId(Long userId);

    @Query(value = "select academic_id as academicId,date,marks,term,course.subject,academic.user_id as userId,student_grade_history.grade from academic join course on academic.course_id=course.course_id join student_grade_history on academic.user_id=student_grade_history.user_id where student_grade_history.year>=date_format(date,\"%Y-1-1\") and student_grade_history.grade=?1",nativeQuery = true)
    List<Map<String, Object>> getByGrade(String grade);
}