package com.sms.courseservice.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.courseservice.repository.AcademicRepository;
import com.sms.courseservice.service.AcademicService;
import com.sms.enums.user_management.GradeEnum;
import com.sms.enums.user_management.TermEnum;
import com.sms.enums.user_management.UserEnum;
import com.sms.exception.NotFoundException;
import com.sms.pojo.AcademicPojo;
import com.sms.pojo.UserPojo;
import com.sms.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private AcademicRepository academicRepository;
    @Autowired
    private HttpServletRequest request;
    @Override
    public List<AcademicPojo> getByUserId(Long userId) {
        List<Map<String,Object>> academicList= academicRepository.findByUserId(userId);
        SmsResponse res=webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/users/fetch/"+userId)
                .headers(headers -> {
                    headers.set("SMS_API_KEY","thisisasecret");
                    headers.set("Authorization",request.getHeader("Authorization"));
                })
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        if(!res.getStatus()){
            throw new NotFoundException("User with the given ID not found!");
        }
        ObjectMapper mapper=new ObjectMapper();
        UserPojo userRes=mapper.convertValue(res.getPayload(),UserPojo.class);
        List<AcademicPojo> academicRes=new ArrayList<>();
        academicList.forEach(academic->{
            academicRes.add(AcademicPojo.builder()
                    .academicId(((BigInteger)academic.get("academicId")).longValue())
                    .subject((String)academic.get("subject"))
                    .marks((Integer)academic.get("marks"))
                    .date((Date)academic.get("date"))
                    .term(TermEnum.valueOf((String)academic.get("term")))
                    .grade(GradeEnum.valueOf((String)academic.get("grade")))
                    .user(userRes)
                    .build()
            );
        });
        return academicRes;
    }

    @Override
    public List<AcademicPojo> getByGrade(GradeEnum grade) {
        String gradeStr=grade.toString();
        List<Map<String,Object>> academicList= academicRepository.getByGrade(gradeStr);
        Long userIds[]=new Long[academicList.size()];
        AtomicReference<Integer> userIdIndex= new AtomicReference<>(0);
        academicList.forEach(academics->{
            userIds[userIdIndex.get()]=((BigInteger)academics.get("userId")).longValue();
            userIdIndex.getAndSet(userIdIndex.get() + 1);
        });
        SmsResponse res=webClientBuilder.build().post()
                .uri("http://USER-SERVICE/api/users/fetchMany")
                .headers(headers -> {
                    headers.set("SMS_API_KEY","thisisasecret");
                    headers.set("Authorization",request.getHeader("Authorization"));
                })
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userIds)
                .retrieve()
                .bodyToMono(SmsResponse.class)
                .block();
        if(!res.getStatus()){
            throw new NotFoundException("User with the given ID not found!");
        }
        ObjectMapper mapper=new ObjectMapper();

        List<Map<String,Object>> userListRes=mapper.convertValue(res.getPayload(),List.class);
        userListRes.forEach(user-> System.out.println(user.get("email")+" "+user.get("userId")));

        List<AcademicPojo> academicRes=new ArrayList<>();
        academicList.forEach(academic->{
            academicRes.add(AcademicPojo.builder()
                    .academicId(((BigInteger)academic.get("academicId")).longValue())
                    .subject((String)academic.get("subject"))
                    .marks((Integer)academic.get("marks"))
                    .date((Date)academic.get("date"))
                    .term(TermEnum.valueOf((String)academic.get("term")))
                    .grade(GradeEnum.valueOf((String)academic.get("grade")))
                    .user(getByUserId(((BigInteger)academic.get("userId")).longValue(),userListRes))
                    .build()
            );
        });
        return academicRes;
    }

    private UserPojo getByUserId(Long userId,List<Map<String,Object>> users){
        for(Map<String,Object> user:users){
            if(user.get("userId")==userId)
                return UserPojo.builder()
                        .userId(((Integer)user.get("userId")).longValue())
                        .firstName((String)user.get("firstName"))
                        .lastName((String)user.get("lastName"))
                        .email((String)user.get("email"))
                        .role(UserEnum.valueOf((String)user.get("role")))
                        .build();
        }
        UserPojo userPojo=new UserPojo();
        return userPojo;
    }
}