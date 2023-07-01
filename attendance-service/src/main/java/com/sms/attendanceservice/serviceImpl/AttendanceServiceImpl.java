package com.sms.attendanceservice.serviceImpl;

import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.exception.NotFoundException;
import com.sms.model.user_management.User;
import com.sms.repository.user_management.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebClient.Builder webclient;

//    @Value("${sms.auth.token.header.name}")
//    String API_HEADER;
//
//    @Value("${sms.auth.token.value}")
//    String API_HEADER_VALUE;

    @Override
    public Attendance checkInAttendance(AttendancePojo attendancePojo) {
        Attendance attendance;
        if(attendancePojo.getAttendanceId()!=null){
            attendance = attendanceRepo.findById(attendancePojo.getAttendanceId()).get();
        }
        else{
            attendance = new Attendance();
        }
        attendance.setCheckIn(LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        System.out.println("--------this is a user Id : "+ attendancePojo.getUsers().get(0));
        attendancePojo.getUsers().forEach(userId->{
            System.out.println("The user id is: "+userId);
            userList.add(userRepository.findById(userId).get());
        } );
        attendance.setUserId(userList);
        Attendance attendanceTaken = attendanceRepo.save(attendance);

        return attendanceTaken;
    }

    @Override
    public List<Attendance> getAllAttendance() {

        List<Attendance> attendances = attendanceRepo.findAll();
        if (attendances==null)
        {
            throw new NotFoundException("attendance database is empty");
        }

        //getAttendanceByUserId
//        User user = webclient.build()
//                .get()
//                .uri("http://10.10.25.45:8092/api/users/fetchAllStudents")
//                .header(API_HEADER,API_HEADER_VALUE)
//                .retrieve()
//                .bodyToMono(User.class)
//                .block();
//        System.out.println("Tero bau "+user.getEmail());

        return attendances;
    }

    @Override
    public List<Attendance> getAttendanceByUserId(Long userId) {


        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceBetweenDates(String from, String to) {
        return null;
    }

    @Override
    public List<Attendance> getAllAttendanceBetweenDatesByUserId(Long userId, String from, String to) {
        return null;
    }
}
