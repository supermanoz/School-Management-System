package com.sms.courseservice.service.serviceImpl;

import com.sms.courseservice.repository.CourseRepository;
import com.sms.courseservice.service.CourseService;
import com.sms.exception.NotFoundException;
import com.sms.model.user_management.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> fetchAll() {
        List<Course> courseList=courseRepository.findAll();
        if(courseList.isEmpty()){
            throw new RuntimeException("No course in the list!");
        }
        return courseRepository.findAll();
    }

    public Course fetch(Long courseId){
        Optional<Course> courseOptional=courseRepository.findById(courseId);
        if(!courseOptional.isPresent()){
            throw new NotFoundException("Course Not Found!");
        }
        return courseOptional.get();
    }
}
