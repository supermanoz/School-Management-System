package com.sms.courseservice.service.serviceImpl;

import com.sms.courseservice.repository.PeriodRepository;
import com.sms.courseservice.service.PeriodService;
import com.sms.model.user_management.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
