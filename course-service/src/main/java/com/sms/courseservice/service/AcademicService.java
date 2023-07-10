package com.sms.courseservice.service;

import com.sms.enums.user_management.GradeEnum;
import com.sms.model.user_management.Academic;
import com.sms.pojo.AcademicPojo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface AcademicService {
    List<AcademicPojo> getByUserId(Long userId);

    List<AcademicPojo> getByGrade(GradeEnum grade);
}
