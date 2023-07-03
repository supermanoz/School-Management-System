package com.sms.repository.user_management;

import com.sms.enums.user_management.UserEnum;
import com.sms.model.user_management.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserEnum student);
}
