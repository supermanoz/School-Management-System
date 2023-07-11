package com.sms.courseservice.repository;

import com.sms.model.user_management.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface PeriodRepository extends JpaRepository<Period,Long> {
    @Query(value="select period.period_name as period from period where begins_at<=current_timestamp() and ends_at>=current_timestamp()",nativeQuery = true)
    public Map<String,Object> findOneByCurrentTime();
}
