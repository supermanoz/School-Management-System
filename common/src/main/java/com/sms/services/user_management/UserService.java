package com.sms.services.user_management;

import com.sms.model.user_management.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    List<Map<String, Objects>> fetchAll();
}
