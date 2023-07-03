package com.sms.services.user_management.serviceImpl;

import com.sms.repository.user_management.UserRepository;
import com.sms.services.user_management.UserService;
import com.sms.model.user_management.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User getByEmail(String email){
        return userRepository.findByEmail(email).get();
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
}
