package com.sms.userservice.service;

import com.sms.model.user_management.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;
//import com.sms.userservice.User;

public interface UserService {
    public Map<String, Objects> getByEmail(String email);
    public List<User> getManyById(List<Long> ids);
    public User save(User user);
    public User getById(Long id);
    public List<User> getAllStudents();
}
