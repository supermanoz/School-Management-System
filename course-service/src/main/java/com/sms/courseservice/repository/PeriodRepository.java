package com.sms.courseservice.repository;

import com.sms.model.user_management.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period,Long> {
}
