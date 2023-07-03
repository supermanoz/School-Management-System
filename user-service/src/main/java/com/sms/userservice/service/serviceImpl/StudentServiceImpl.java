package com.sms.userservice.service.serviceImpl;

import com.sms.userservice.repository.StudentRepository;
import com.sms.userservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Map<String, Objects> getById(Long studentId) {
        Map<String, Objects> student = studentRepository.fetchById(studentId);
        return student;
    }
}
