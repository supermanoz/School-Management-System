package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.model.user_management.User;
import com.sms.repository.user_management.UserRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Attendance checkInAttendance(Long userId,Attendance attendance) {

          User currentUser = userRepo.findById(userId).get();
          if(currentUser==null){
              throw new RuntimeException("not found");
          }

        Attendance addAttendance = attendanceRepo.save(attendance);

        return addAttendance;
    }

    @Override
    public List<Attendance> getAllAttendance() {

        List<Attendance> attendance = attendanceRepo.findAll();

        return attendance;
    }


}
