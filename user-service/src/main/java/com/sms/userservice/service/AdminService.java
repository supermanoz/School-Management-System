package com.sms.userservice.service;

import com.sms.enums.user_management.UserEnum;
import com.sms.model.user_management.User;
import com.sms.userservice.pojo.StudentPojo;
import com.sms.userservice.pojo.TeacherGradeHistoryPojo;
import com.sms.userservice.pojo.TeacherPojo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface AdminService {

    List<Map<String, Objects>> getUserByRole(UserEnum role);

    StudentPojo saveStudent(StudentPojo studentPojo);

    TeacherPojo saveTeacher(TeacherPojo teacherPojo);

    TeacherGradeHistoryPojo saveTeacherGradeHistory(TeacherGradeHistoryPojo teacherGradeHistoryPojo);

    List<Map<String, Objects>> getAllTeacherGradeHistory();

    List<Map<String, Objects>> getAllTeacher();
}
