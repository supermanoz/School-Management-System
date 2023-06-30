package com.sms.userservice.service.serviceImpl;

import com.sms.userservice.models.TeacherGradeHistory;
import com.sms.userservice.repository.TeacherGradeHistoryRepository;
import com.sms.userservice.repository.UserRepository;
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

    @Override
    public List<Map<String, Objects>> getAll() {
        return userRepository.getAllTeacher();
    }

    @Override
    public Map<String, Objects> getById(Long teacherId) {
        return userRepository.getTeacherById(teacherId);
    }

    @Override
    public List<TeacherGradeHistory> getGradeHistoryByTeacherId(Long teacherId) {
        List<TeacherGradeHistory>  result = teacherGradeHistoryRepository.findByTeacherId(teacherId);
        return result;
    }
}
