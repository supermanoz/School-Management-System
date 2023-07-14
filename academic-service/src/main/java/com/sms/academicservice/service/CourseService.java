package com.sms.academicservice.service;

import com.sms.academicservice.dto.CourseRequest;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.model.academic_management.Course;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface CourseService {
    public Course save(CourseRequest course) throws InvalidAttributeValueException;
    public List<Course> fetchAll();
    public Course fetch(Long courseId);
    public Course delete(Long courseId);
    public List<Course> fetchManyByGrade(GradeEnum grade);
}
