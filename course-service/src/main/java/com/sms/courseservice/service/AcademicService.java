package com.sms.courseservice.service;

import com.sms.enums.user_management.GradeEnum;
import com.sms.pojo.AcademicDto;
import com.sms.pojo.AcademicPojo;

import java.util.List;

public interface AcademicService {
    List<AcademicPojo> getByUserId(Long userId);

    List<AcademicPojo> getByGrade(GradeEnum grade);

    List<AcademicDto> getByUserIdAndGrade(Long userId, GradeEnum grade);
}
