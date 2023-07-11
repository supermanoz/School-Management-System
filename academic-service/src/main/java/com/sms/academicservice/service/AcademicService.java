package com.sms.academicservice.service;

import com.sms.academicservice.dto.AcademicRequest;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.pojo.academic_management.AcademicPojo;

import java.util.List;

public interface AcademicService {
    List<AcademicPojo> getByUserId(Long userId);
    List<AcademicPojo> getByGrade(GradeEnum grade);
    List<AcademicPojo> getByUserIdAndGrade(Long userId, GradeEnum grade);
    AcademicPojo save(AcademicRequest academicRequest);
}
