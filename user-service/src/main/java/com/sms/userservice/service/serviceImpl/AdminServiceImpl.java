package com.sms.userservice.service.serviceImpl;

import com.sms.enums.user_management.UserEnum;
import com.sms.model.user_management.User;
import com.sms.repository.user_management.RoleRepository;
import com.sms.repository.user_management.UserRepository;
import com.sms.userservice.models.Student;
import com.sms.userservice.models.Teacher;
import com.sms.userservice.models.TeacherGradeHistory;
import com.sms.userservice.pojo.StudentPojo;
import com.sms.userservice.pojo.TeacherGradeHistoryPojo;
import com.sms.userservice.pojo.TeacherPojo;
import com.sms.userservice.repository.StudentRepository;
import com.sms.userservice.repository.TeacherGradeHistoryRepository;
import com.sms.userservice.repository.TeacherRepository;
import com.sms.userservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherGradeHistoryRepository teacherGradeHistoryRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Map<String, Objects>> getUserByRole(UserEnum role) {

        return userRepository.findByRole(role.toString());
    }

    @Override
    public StudentPojo saveStudent(StudentPojo studentPojo) {
        Student student;
        User user;
        if(studentPojo.getStudentId()!=null){
            student = studentRepository.findById(studentPojo.getStudentId()).get();
        }
        else {
            student = new Student();
        }
        if(studentPojo.getUserId()!=null){
            user = userRepository.findById(studentPojo.getUserId()).get();
        }
        else {
            user = new User();
        }

        user.setEmail(studentPojo.getEmail());
        user.setRoles(roleRepository.findByRole(UserEnum.STUDENT));
        user.setFirstName(studentPojo.getFirstName());
        user.setLastName(studentPojo.getLastName());
        User savedUer = userRepository.save(user);
        student.setUser(user);
        student.setCurrentGrade(studentPojo.getCurrentGrade());
        Student savedStudent = studentRepository.save(student);
        studentPojo.setStudentId(savedStudent.getStudentId());
        studentPojo.setUserId(savedUer.getUserId());
        return studentPojo;
    }

    @Override
    public TeacherPojo saveTeacher(TeacherPojo teacherPojo) {
        Teacher teacher;
        User user;
        if(teacherPojo.getTeacherId()!=null){
            teacher = teacherRepository.findById(teacherPojo.getTeacherId()).get();
        }
        else {
            teacher = new Teacher();
        }
        if(teacherPojo.getUserId()!=null){
            user = userRepository.findById(teacherPojo.getUserId()).get();
        }
        else {
            user = new User();
        }

        user.setEmail(teacherPojo.getEmail());
        user.setRoles(roleRepository.findByRole(UserEnum.STUDENT));
        user.setFirstName(teacherPojo.getFirstName());
        user.setLastName(teacherPojo.getLastName());
        User savedUer = userRepository.save(user);
        teacher.setUser(savedUer);
        teacher.setAssignedGrade(teacherPojo.getAssignedGrade());
        teacher.setCurrentSalary(teacherPojo.getCurrentSalary());
        Teacher savedTeacher = teacherRepository.save(teacher);
        teacherPojo.setTeacherId(savedTeacher.getTeacherId());
        teacherPojo.setUserId(savedUer.getUserId());
        return teacherPojo;
    }

    @Override
    public TeacherGradeHistoryPojo saveTeacherGradeHistory(TeacherGradeHistoryPojo teacherGradeHistoryPojo) {
        TeacherGradeHistory teacherGradeHistory;
        Teacher teacher = teacherRepository.findById(teacherGradeHistoryPojo.getTeacherId()).get();
        if(teacherGradeHistoryPojo.getTeacherGradeHistoryId()!=null){
            teacherGradeHistory = teacherGradeHistoryRepository.findById(teacherGradeHistoryPojo.getTeacherGradeHistoryId()).get();
        }
        else {
            teacherGradeHistory = new TeacherGradeHistory();
        }
        teacherGradeHistory.setGrade(teacherGradeHistoryPojo.getGrade());
        teacherGradeHistory.setDate(new Date(teacherGradeHistoryPojo.getDate()));
        teacherGradeHistory.setTeacher(teacher);
        TeacherGradeHistory saved = teacherGradeHistoryRepository.save(teacherGradeHistory);
        teacherGradeHistoryPojo.setTeacherGradeHistoryId(saved.getTeacherGradeId());
        return teacherGradeHistoryPojo;
    }

    @Override
    public List<Map<String, Objects>> getAllTeacherGradeHistory() {
        List<Map<String, Objects>> res = teacherGradeHistoryRepository.fetchAll();
        return res;
    }

    @Override
    public List<Map<String, Objects>> getAllTeacher() {
        return teacherRepository.getAllTeacher();
    }
}
