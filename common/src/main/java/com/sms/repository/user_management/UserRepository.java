package com.sms.repository.user_management;

import com.sms.model.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select u.* from users u;",nativeQuery = true)
    List<Map<String, Objects>> fetchALl();
}
