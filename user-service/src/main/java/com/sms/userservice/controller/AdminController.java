package com.sms.userservice.controller;

import com.sms.enums.user_management.UserEnum;
import com.sms.response.SmsResponse;
import com.sms.userservice.pojo.StudentPojo;
import com.sms.userservice.pojo.TeacherGradeHistoryPojo;
import com.sms.userservice.pojo.TeacherPojo;
import com.sms.userservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("getUserByRole/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable("role") UserEnum role){
        List<Map<String, Objects>> users = adminService.getUserByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true,users));
    }

    @PostMapping("student/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentPojo studentPojo){
        StudentPojo savedStudent = adminService.saveStudent(studentPojo);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, savedStudent));
    }

    @PostMapping("teacher/save")
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherPojo teacherPojo){
        TeacherPojo savedTeacher = adminService.saveTeacher(teacherPojo);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, savedTeacher));
    }

    @PostMapping("teacher-grade-history/save")
    public ResponseEntity<?> saveTeacherGradeHistory(@RequestBody TeacherGradeHistoryPojo teacherGradeHistoryPojo){
        TeacherGradeHistoryPojo saved = adminService.saveTeacherGradeHistory(teacherGradeHistoryPojo);
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, saved));
    }

    @GetMapping("teacher-grade-history/fetchAll")
    public ResponseEntity<?> getHistoryByTeacherId(){
        List<Map<String, Objects>> results = adminService.getAllTeacherGradeHistory();
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, results));
    }

    @GetMapping("teacher/getAll")
    public ResponseEntity<?> getAll(){
        List<Map<String, Objects>> teachers = adminService.getAllTeacher();
        return ResponseEntity.status(HttpStatus.OK).body(new SmsResponse("",true, teachers));
    }
}
