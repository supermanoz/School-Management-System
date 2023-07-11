package com.sms.courseservice.service;

import com.sms.model.user_management.Period;

import java.util.List;
import java.util.Map;

public interface PeriodService {
    public Period save(Period period);
    public List<Period> fetchAll();
    public String getByCurrentTime();
}
