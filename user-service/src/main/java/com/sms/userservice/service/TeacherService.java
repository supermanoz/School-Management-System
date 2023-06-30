package com.sms.userservice.service;

import com.sms.userservice.models.TeacherGradeHistory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TeacherService {
    List<Map<String, Objects>> getAll();

    Map<String, Objects> getById(Long teacherId);

    List<TeacherGradeHistory> getGradeHistoryByTeacherId(Long teacherId);
}
