package com.sms.courseservice.service;

import com.sms.model.user_management.Course;

import java.util.List;

public interface CourseService {
    public Course save(Course course);
    public List<Course> fetchAll();
    public Course fetch(Long courseId);
}
