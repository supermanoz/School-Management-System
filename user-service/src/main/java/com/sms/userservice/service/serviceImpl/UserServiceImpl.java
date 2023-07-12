package com.sms.userservice.service.serviceImpl;

import com.sms.enums.user_management.UserEnum;
import com.sms.exception.NotFoundException;
import com.sms.pojo.user_management.StudentPojo;
import com.sms.userservice.repository.UserRepository;
import com.sms.userservice.service.UserService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Map<String, Objects> getByEmail(String email){
        Map<String, Objects> res=userRepository.getUserByEmail(email);
        return res;
    }

    @Override
    public List<User> getManyById(List<Long> ids) {
        return userRepository.findUsersByIds(ids);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.findAllStudents();
    }

    @Override
    public StudentPojo getStudent(Long id) {
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent() || !user.get().getRoles().equals(UserEnum.STUDENT)){
            throw new NotFoundException("Student not found!");
        }
       return StudentPojo.builder()
                .userId(user.get().getUserId())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .build();
    }
}
