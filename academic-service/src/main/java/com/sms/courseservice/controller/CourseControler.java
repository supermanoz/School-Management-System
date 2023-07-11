package com.sms.courseservice.controller;

import com.sms.courseservice.service.CourseService;
import com.sms.model.user_management.Course;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseControler {
    @Autowired
    private CourseService courseService;
    @PostMapping("/save")
    public ResponseEntity<SmsResponse> addCourse(@RequestBody Course course){
        return ResponseEntity.ok().body(new SmsResponse("Course added successfully!",true,courseService.save(course)));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<SmsResponse> fetchAll(){
        return ResponseEntity.ok().body(new SmsResponse("Course List",true,courseService.fetchAll()));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<SmsResponse> fetchOne(@PathVariable Long id){
        return ResponseEntity.ok().body(new SmsResponse("Course",true,courseService.fetch(id)));
    }
}
