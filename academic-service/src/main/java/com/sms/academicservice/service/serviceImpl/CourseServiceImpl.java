package com.sms.academicservice.service.serviceImpl;

import com.sms.academicservice.dto.CourseRequest;
import com.sms.academicservice.repository.PeriodRepository;
import com.sms.academicservice.service.CourseService;
import com.sms.academicservice.repository.CourseRepository;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.enums.academic_management.PeriodEnum;
import com.sms.exception.NotFoundException;
import com.sms.model.academic_management.Course;
import com.sms.model.academic_management.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public Course save(CourseRequest course) throws InvalidAttributeValueException {
        Optional<Period> periodOptional=periodRepository.findById(course.getPeriodId());
        if(!periodOptional.isPresent() || periodOptional.get().getPeriodName().equals(PeriodEnum.BREAK) || courseRepository.findByGradeAndPeriod(course.getGrade(),periodOptional.get()).isPresent()){
            throw new InvalidAttributeValueException("Invalid Period");
        }
        Course toDoCourse=Course.builder()
                .subject(course.getSubject())
                .grade(course.getGrade())
                .period(periodOptional.get())
                .build();
        return courseRepository.save(toDoCourse);
    }

    @Override
    public List<Course> fetchAll() {
        List<Course> courseList=courseRepository.findAll();
        if(courseList.isEmpty()){
            throw new NotFoundException("No course in the list!");
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

    @Override
    public Course delete(Long courseId) {
        Optional<Course> courseOptional=courseRepository.findById(courseId);
        if(!courseOptional.isPresent()){
            throw new NotFoundException("Course does not exist!");
        }
        courseRepository.deleteById(courseId);
        return courseOptional.get();
    }

    @Override
    public List<Course> fetchManyByGrade(GradeEnum grade) {
        List<Course> courseList=courseRepository.findByGrade(grade);
        if(courseList.isEmpty()){
            throw new NotFoundException("No course in the list!");
        }
        return courseList;
    }
}
