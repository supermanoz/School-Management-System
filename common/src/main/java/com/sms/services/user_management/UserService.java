package com.sms.services.user_management;

import com.sms.model.user_management.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface UserService {

    public User getByEmail(String email);
    public List<User> getManyById(List<Long> ids);
    public User save(User user);
    public User getById(Long id);
    public List<User> getAllStudents();
}
