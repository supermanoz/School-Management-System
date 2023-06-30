package com.sms.userservice.controller;

import com.sms.userservice.models.TeacherGradeHistory;
import com.sms.userservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        List<Map<String, Objects>> teachers = teacherService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @GetMapping("getById/{teacherId}")
    public ResponseEntity<?> getById(@PathVariable("teacherId") Long teacherId){
        Map<String, Objects> teacher = teacherService.getById(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @GetMapping("getGradeHistory/{teacherId}")
    public ResponseEntity<?> getGradeHistoryByTeacherId(@PathVariable("teacherId") Long teacherId){
        List<TeacherGradeHistory> resultList = teacherService.getGradeHistoryByTeacherId(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

}
