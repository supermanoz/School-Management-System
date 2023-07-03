package com.sms.userservice.service;

import java.util.Map;
import java.util.Objects;

public interface StudentService {
    Map<String, Objects> getById(Long studentId);
}
