package com.example.academic.services.servicesImpl;

import com.example.academic.repository.AcademicRepository;
import com.example.academic.services.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private AcademicRepository academicRepository;
    @Override
    public List<Map<String, Objects>> getByUserId(Long userId) {
        return academicRepository.findByUserId(userId);
    }

    @Override
    public List<Map<String, Objects>> getByGrade(String grade) {
        return academicRepository.getByGrade(grade);
    }
}
