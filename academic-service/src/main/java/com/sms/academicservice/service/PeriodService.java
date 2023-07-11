package com.sms.academicservice.service;

import com.sms.model.academic_management.Period;

import java.util.List;

public interface PeriodService {
    public Period save(Period period);
    public List<Period> fetchAll();
    public String getByCurrentTime();
}
