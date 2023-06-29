package com.sms.userservice.service;

import com.sms.exception.NotFoundException;
import com.sms.model.user_management.User;

import java.util.List;
//import com.sms.userservice.User;

public interface UserService {
    public User getByEmail(String email);
    public List<User> getManyById(List<Long> ids);
    public User save(User user);
    public User getById(Long id);
    public List<User> getAllStudents();
}
