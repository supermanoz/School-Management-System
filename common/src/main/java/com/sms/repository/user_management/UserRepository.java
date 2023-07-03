package com.sms.repository.user_management;

import com.sms.model.user_management.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u where u.email = ?1", nativeQuery = true)
    Map<String, Objects> fetchByEmail(String email);


    @Query(value = "select u.* from users u where u.email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from users where user_id in :ids")
    List<User> findUsersByIds(@Param("ids") List<Long> ids);

    @Query(nativeQuery = true,value = "select * from users where role_id=1")
    List<User> findAllStudents();

    @Query(value = "select u.*, t.salary, t.assignedGrade from users u inner join teachers on u.user_id = t.user_id where u.user_id = ?1",nativeQuery = true)
    Map<String, Objects> getTeacherById(Long teacherId);

    @Query(value = "select u.* from users u  inner join roles r on u.role_id = r.role_id where r.role = ?1 ", nativeQuery = true)
    List<Map<String, Objects>> findByRole(String role);
}
