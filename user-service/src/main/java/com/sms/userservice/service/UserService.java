package com.sms.userservice.service;

import com.sms.model.user_management.User;
//import com.sms.userservice.User;

public interface UserService {
    public User getByEmail(String email);
    public User save(User user);
}
