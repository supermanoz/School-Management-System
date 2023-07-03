package com.sms.userservice.service.serviceImpl;

import com.sms.repository.user_management.UserRepository;
import com.sms.userservice.models.Teacher;
import com.sms.userservice.models.TeacherGradeHistory;
import com.sms.userservice.repository.TeacherGradeHistoryRepository;
import com.sms.userservice.repository.TeacherRepository;
import com.sms.userservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherGradeHistoryRepository teacherGradeHistoryRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Map<String, Objects> getById(Long teacherId) {
        return userRepository.getTeacherById(teacherId);
    }

    @Override
    public List<TeacherGradeHistory> getGradeHistoryByTeacherId(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).get();
        List<TeacherGradeHistory>  result = teacherGradeHistoryRepository.findByTeacherId(teacherId);
        return result;
    }
}
