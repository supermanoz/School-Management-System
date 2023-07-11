package com.sms.courseservice.repository;

import com.sms.model.user_management.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
