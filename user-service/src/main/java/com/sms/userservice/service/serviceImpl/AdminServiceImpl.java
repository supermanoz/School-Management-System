package com.sms.userservice.service.serviceImpl;

import com.sms.userservice.repository.StudentRepository;
import com.sms.userservice.repository.TeacherGradeHistoryRepository;
import com.sms.userservice.repository.TeacherRepository;
import com.sms.userservice.repository.UserRepository;
import com.sms.userservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
