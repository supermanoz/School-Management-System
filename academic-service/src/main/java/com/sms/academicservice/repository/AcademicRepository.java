package com.sms.academicservice.repository;

import com.sms.model.academic_management.Academic;
import com.sms.model.academic_management.Course;
import com.sms.pojo.academic_management.AcademicDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {
    @Query(value = "select academic.academic_id as academicId,academic.date,academic.marks,academic.term,course.subject,academic.user_id as userId,student_grade_history.grade from course join academic on academic.course_id=course.course_id join student_grade_history on academic.user_id=student_grade_history.user_id where academic.user_id=:userId and academic.date>=date_format(curdate(),\"%Y-01-01\") and student_grade_history.year>=date_format(curdate(),\"%Y-01-01\") and academic.term=:term",nativeQuery = true)
    public List<Map<String,Object>> findByUserId(Long userId,String term);

//    @Query(value = "select academic_id as academicId,date,marks,term,course.subject,academic.user_id as userId,student_grade_history.grade from academic join course on academic.course_id=course.course_id join student_grade_history on academic.user_id=student_grade_history.user_id where student_grade_history.year>=date_format(date,\"%Y-1-1\") and student_grade_history.grade=?1",nativeQuery = true)
//    List<Map<String, Object>> getByGrade(String grade);

  @Query(name="getAllByUserIdAndTermAndGrade",nativeQuery = true)
    public List<AcademicDto> getByUserIdAndTermAndGrade(Long userId,String term,String grade);
    public Optional<Academic> findByUserIdAndCourse(Long userId, Course course);
}