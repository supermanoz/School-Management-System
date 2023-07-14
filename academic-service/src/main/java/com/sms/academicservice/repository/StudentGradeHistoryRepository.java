package com.sms.academicservice.repository;

import com.sms.model.academic_management.StudentGradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentGradeHistoryRepository extends JpaRepository<StudentGradeHistory,Long> {

    @Query(value="select * from student_grade_history where user_id=:studentId and year>=date_format(curdate(),\"%Y-01-01\")",nativeQuery = true)
    public Optional<StudentGradeHistory> findByStudnetId(Long studentId);
}
