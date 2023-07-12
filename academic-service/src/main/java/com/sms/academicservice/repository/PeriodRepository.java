package com.sms.academicservice.repository;

import com.sms.model.academic_management.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface PeriodRepository extends JpaRepository<Period,Long> {
    @Query(name="getCurrentPeriod",nativeQuery = true)
    public Period findOneByCurrentTime();
}
