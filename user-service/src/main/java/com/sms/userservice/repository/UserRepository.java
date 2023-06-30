package com.sms.userservice.repository;

import com.sms.model.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from users where user_id in :ids")
    public List<User> findUsersByIds(@Param("ids") List<Long> ids);

    @Query(nativeQuery = true,value = "select * from users where role_id=1")
    public List<User> findAllStudents();

    @Query(value = "select u.*, t.salary, t.assignedGrade from users u inner join teachers on u.user_id = t.user_id",nativeQuery = true)
    List<Map<String, Objects>> getAllTeacher();

    @Query(value = "select u.*, t.salary, t.assignedGrade from users u inner join teachers on u.user_id = t.user_id where u.user_id = ?1",nativeQuery = true)
    Map<String, Objects> getTeacherById(Long teacherId);
}
