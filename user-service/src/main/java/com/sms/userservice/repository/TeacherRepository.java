package com.sms.userservice.repository;

import com.sms.userservice.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "select t.teacher_id, t.assigned_grade, t.current_salary, u.first_name, u.last_name, u.email, u.password, r.role from teachers t inner join users u on t.user_id = u.user_id inner join roles r on r.role_id = u.role_id", nativeQuery = true)
    List<Map<String, Objects>> getAllTeacher();
}
