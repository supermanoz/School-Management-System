package com.sms.userservice.repository;

import com.sms.userservice.models.TeacherGradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface TeacherGradeHistoryRepository extends JpaRepository<TeacherGradeHistory, Long> {
    @Query(value = "select tg.* from teacher_grade_history tg where tg.teacher_id = ?1",nativeQuery = true)
    List<TeacherGradeHistory> findByTeacherId(Long teacherId);

    @Query(value = "select tg.* from teacher_grade_history tg",nativeQuery = true)
    List<Map<String, Objects>> fetchAll();
}
