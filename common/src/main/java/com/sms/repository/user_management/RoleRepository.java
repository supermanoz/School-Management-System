package com.sms.repository.user_management;

import com.sms.model.user_management.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RoleRepository extends JpaRepository<Role, Long> {
}
