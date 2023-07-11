package com.sms.academicservice.repository;

import com.sms.model.academic_management.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
