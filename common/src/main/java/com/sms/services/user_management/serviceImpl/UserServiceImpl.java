package com.sms.services.user_management.serviceImpl;

import com.sms.repository.user_management.UserRepository;
import com.sms.services.user_management.UserService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail();
    }

    @Override
    public List<Map<String, Objects>> fetchAll() {
        return userRepository.fetchALl();
    }
}
