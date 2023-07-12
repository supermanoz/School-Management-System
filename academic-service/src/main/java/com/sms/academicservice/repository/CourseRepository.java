package com.sms.academicservice.repository;

import com.sms.enums.academic_management.GradeEnum;
import com.sms.model.academic_management.Course;
import com.sms.model.academic_management.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public Optional<Course> findByGradeAndPeriod(GradeEnum grade, Period period);
    public List<Course> findByGrade(GradeEnum grade);
}
