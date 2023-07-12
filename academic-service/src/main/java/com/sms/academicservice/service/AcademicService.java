package com.sms.academicservice.service;

import com.sms.academicservice.dto.AcademicRequest;
import com.sms.academicservice.pojo.TermResult;
import com.sms.enums.academic_management.GradeEnum;
import com.sms.enums.academic_management.TermEnum;
import com.sms.pojo.academic_management.AcademicPojo;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public interface AcademicService {
    TermResult getByUserIdAndTerm(Long userId, TermEnum term);
    TermResult getByUserIdAndTermAndGrade(Long userId,TermEnum term, GradeEnum grade);
    AcademicPojo save(AcademicRequest academicRequest) throws InvalidAttributeValueException;
    GradeEnum getGrade(Long userId);
}
