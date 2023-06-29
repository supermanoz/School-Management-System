package com.example.academic.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface AcademicService {
    List<Map<String, Objects>> getByUserId(Long userId);

    List<Map<String, Objects>> getByGrade(String grade);
}
