package com.sms.userservice.service.serviceImpl;

import com.sms.userservice.repository.UserRepository;
import com.sms.userservice.service.UserService;
import com.sms.model.user_management.User;
//import com.sms.userservice.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
