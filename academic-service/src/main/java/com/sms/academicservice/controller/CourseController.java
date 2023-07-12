package com.sms.academicservice.controller;

import com.sms.academicservice.dto.CourseRequest;
import com.sms.academicservice.service.CourseService;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/save")
    public ResponseEntity<SmsResponse> addCourse(@RequestBody CourseRequest course) throws InvalidAttributeValueException {
        return ResponseEntity.ok().body(new SmsResponse("Course added successfully!",true,courseService.save(course)));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<SmsResponse> fetchAll(){
        return ResponseEntity.ok().body(new SmsResponse("Course List",true,courseService.fetchAll()));
    }

    @GetMapping("/fetchByGrade")
    public ResponseEntity<SmsResponse> fetchAll(@RequestParam("grade")GradeEnum grade){
        return ResponseEntity.ok().body(new SmsResponse("Course List",true,courseService.fetchManyByGrade(grade)));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<SmsResponse> fetchOne(@PathVariable Long id){
        return ResponseEntity.ok().body(new SmsResponse("Course",true,courseService.fetch(id)));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<SmsResponse> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(new SmsResponse("Course deleted",true,courseService.delete(id)));
    }
}
