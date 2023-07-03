package com.sms.userservice.repository;

import com.sms.userservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select s.student_id, u.user_id, u.first_name, u.last_name, u.email, u.password, s.current_grade from students s inner join users u on s.user_id = u.user_id",nativeQuery = true)
    Map<String, Objects> fetchById(Long studentId);
}
