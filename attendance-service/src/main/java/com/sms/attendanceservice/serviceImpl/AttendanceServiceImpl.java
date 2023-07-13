package com.sms.attendanceservice.serviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.attendanceservice.model.Attendance;
import com.sms.attendanceservice.pojo.AttendancePojo;
import com.sms.attendanceservice.pojo.DateTimePojo;
import com.sms.attendanceservice.repository.AttendanceRepository;
import com.sms.attendanceservice.service.AttendanceService;
import com.sms.exception.NotFoundException;
import com.sms.pojo.user_management.UserPojo;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Value("${sms.auth.token.header.name}")
    private String headerName;

    @Value("${sms.auth.token.value}")
    private String headerValue;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public AttendancePojo checkInOutAttendance( Long userId, HttpServletRequest request) {

        SmsResponse res = null;
        try {

            System.out.println("+++++++++++++++++++++" + "getting UserInformation" + "+++++++++++++++++++++++++>>>>>");

            res = webClientBuilder.build()
                    .get()
                    .uri("http://user-service/api/users/fetch/" + userId)
                    .headers(header -> {
                        header.set(headerName, headerValue);
                        header.set("Authorization", request.getHeader("Authorization"));
                    })
                    .retrieve()
                    .bodyToMono(SmsResponse.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            UserPojo userPojo = mapper.convertValue(res.getPayload(), UserPojo.class);

            if (userPojo.equals(null)) {
                throw new NotFoundException("this user id is not available");
            }

            System.out.println(userPojo.getUserId() + "===== this is a userId========>>");

            System.out.println("+++++++++++++++++++++" + "getting Course" + "+++++++++++++++++++++++++>>>>>");
            SmsResponse courseRes = webClientBuilder.build()
                    .get()
                    .uri("http://academic-service/api/periods/fetchByCurrentTime")
                    .headers(header -> {
                        header.set(headerName, headerValue);
                        header.set("Authorization", request.getHeader("Authorization"));
                    })
                    .retrieve()
                    .bodyToMono(SmsResponse.class)
                    .block();

            ObjectMapper courseMapper = new ObjectMapper();
            String period = courseMapper.convertValue(courseRes.getPayload(), String.class);

            System.out.println(courseRes.getPayload() + "------------ this is period ------------->");

            Optional<Attendance> attendanceCheckIn = attendanceRepo.getAttendanceByCheckInAndUserId(userPojo.getUserId());

            if (!attendanceCheckIn.isPresent()) {

                    Attendance attendance = Attendance.builder()
                            .checkIn(LocalDateTime.now())
                            .userId(userPojo.getUserId())
                            .period(period)
                            .build();

                    attendanceRepo.save(attendance);
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setPeriod(attendance.getPeriod());
                    return pojo;

                } else {
                    Attendance attendanceCheckOut = attendanceCheckIn.get();
                    attendanceCheckOut.setUserId(userPojo.getUserId());
                    attendanceCheckOut.setCheckOut(LocalDateTime.now());
                    attendanceCheckOut.setPeriod(period);
                    attendanceRepo.save(attendanceCheckOut);

                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendanceCheckOut.getAttendanceId());
                    pojo.setUserId(attendanceCheckOut.getUserId());
                    pojo.setCheckIn(attendanceCheckIn.get().getCheckIn());
                    pojo.setCheckOut(attendanceCheckOut.getCheckOut());
                    pojo.setPeriod(attendanceCheckOut.getPeriod());
                    return pojo;
            }
        } catch (WebClientResponseException exception) {
            throw new NotFoundException("user id not found");
        }
    }

    @Override
    public List<AttendancePojo> getAttendanceByUserId(Long userId) {
        List<Attendance> attendanceListByUserId = attendanceRepo.findAttendanceByUserId(userId);

        List<AttendancePojo> attendancePojo = attendanceListByUserId.stream()
                .map(attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());
                    pojo.setPeriod(attendance.getPeriod());
                    return pojo;
                }).collect(Collectors.toList());

        if (attendancePojo.isEmpty()) {
            throw new NotFoundException("this user not attended yet");
        }
        return attendancePojo;
    }

    @Override
    public List<AttendancePojo> getAllAttendance() {

        List<Attendance> listOfAttendance = attendanceRepo.findAll();

        List<AttendancePojo> attendancePojo = listOfAttendance.stream()
                .map(attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());
                    pojo.setPeriod(attendance.getPeriod());

                    return pojo;
                }).collect(Collectors.toList());

        return attendancePojo;
    }


    @Override
    public AttendancePojo getByAttendanceId(Long attendanceId) {

        System.out.println("getByAttendanceId running------------>");
        Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);
        if (!attendance.isPresent()) {
            throw new NotFoundException("this is userId is not Found in Database");
        }
        AttendancePojo attendancePojo = new AttendancePojo();
        attendancePojo.setAttendanceId(attendance.get().getAttendanceId());
        attendancePojo.setUserId(attendance.get().getUserId());
        attendancePojo.setCheckIn(attendance.get().getCheckIn());
        attendancePojo.setCheckOut(attendance.get().getCheckOut());
        attendancePojo.setPeriod(attendance.get().getPeriod());

        return attendancePojo;
    }


    @Override
    public List<AttendancePojo> getAllAttendanceBetweenDates(DateTimePojo dateTimePojo) {


                if (dateTimePojo.getFrom() == null && dateTimePojo.getTo() == null) {
                    throw new NotFoundException("date field cannot be empty");
                }
                List<Attendance> attendancesList = attendanceRepo.getAllAttendanceBetweenDates(dateTimePojo.getFrom(), dateTimePojo.getTo()).get();
                if (attendancesList.isEmpty()) {
                    throw new NotFoundException("there is no attendance between given dates");
                }
                List<AttendancePojo> attendancePojos = attendancesList.stream().map(
                        attendances -> {
                            AttendancePojo pojo = new AttendancePojo();
                            pojo.setAttendanceId(attendances.getAttendanceId());
                            pojo.setUserId(attendances.getUserId());
                            pojo.setCheckIn(attendances.getCheckIn());
                            pojo.setCheckOut(attendances.getCheckOut());
                            pojo.setPeriod(attendances.getPeriod());
                            return pojo;
                        }).collect(Collectors.toList());
                return attendancePojos;
    }

    @Override
    public List<AttendancePojo> getAllAttendanceBetweenDatesByUserId(Long userId, DateTimePojo dateTimePojo) {

        if (dateTimePojo.getFrom() == null && dateTimePojo.getTo() == null && userId == null) {
            throw new NotFoundException("date field and userId field cannot be empty");
        }

        List<Attendance> userAttendanceList = attendanceRepo.getAllAttendanceBetweenDatesByUserId(userId, dateTimePojo.getFrom(),dateTimePojo.getTo()).get();

        if (userAttendanceList.isEmpty()) {
            throw new NotFoundException("there is no attendance between given dates");
        }
        List<AttendancePojo> attendancePojos = userAttendanceList.stream().map(
                attendances -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendances.getAttendanceId());
                    pojo.setUserId(attendances.getUserId());
                    pojo.setCheckIn(attendances.getCheckIn());
                    pojo.setCheckOut(attendances.getCheckOut());
                    pojo.setPeriod(attendances.getPeriod());
                    return pojo;
                }).collect(Collectors.toList());

        return attendancePojos;
    }

    @Override
    public List<AttendancePojo> getAllAttendanceByPeriod(String period) {

        if (period.isEmpty()){
            throw new NotFoundException("please insert a period");
        }
        List<Attendance> attendanceList = attendanceRepo.findAllAttendanceByPeriod(period);

        if (attendanceList.isEmpty()) {
            throw new NotFoundException("there is no attendance at this period");
        }

        List<AttendancePojo> attendancePojos = attendanceList.stream()
                .map( attendance -> {
                    AttendancePojo pojo = new AttendancePojo();
                    pojo.setAttendanceId(attendance.getAttendanceId());
                    pojo.setUserId(attendance.getUserId());
                    pojo.setCheckIn(attendance.getCheckIn());
                    pojo.setCheckOut(attendance.getCheckOut());
                    pojo.setPeriod(attendance.getPeriod());

                    return pojo;
                        }).collect(Collectors.toList());

        return attendancePojos;
    }


}