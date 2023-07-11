package com.sms.academicservice.service.serviceImpl;

import com.sms.academicservice.repository.PeriodRepository;
import com.sms.academicservice.service.PeriodService;
import com.sms.model.academic_management.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;
    @Override
    public Period save(Period period) {
        return periodRepository.save(period);
    }

    @Override
    public List<Period> fetchAll() {
        List<Period> periodList=periodRepository.findAll();
        if(periodList.isEmpty()){
            throw new RuntimeException("No periods in the list");
        }
        return periodRepository.findAll();
    }

    @Override
    public String getByCurrentTime() {
        Map<String,Object> period=periodRepository.findOneByCurrentTime();
        return (String)period.get("period");
    }
}
