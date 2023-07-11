package com.sms.userservice.repository;

import com.sms.model.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select user_id as userId,first_name as firstName,last_name as lastName,email,password,role,group_concat(authority_name) as authorities from (select users.user_id,users.first_name,users.last_name,users.password,users.email,roles.role,authorities.authority_name from users join roles on users.role_id=roles.role_id join relations on roles.role_id=relations.role_id join authorities on relations.authority_id=authorities.authority_id where users.email=?1)a group by email", nativeQuery = true)
    public Map<String, Objects> getUserByEmail(String email);
    @Query(nativeQuery = true,value = "select * from users where user_id in :ids")
    public List<User> findUsersByIds(@Param("ids") List<Long> ids);

    @Query(nativeQuery = true,value = "select * from users where role_id=1")
    public List<User> findAllStudents();
}
