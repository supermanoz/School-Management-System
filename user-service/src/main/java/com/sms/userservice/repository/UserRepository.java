package com.sms.userservice.repository;

import com.sms.model.user_management.User;
//import com.sms.userservice.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User getByEmail(String email);
}
